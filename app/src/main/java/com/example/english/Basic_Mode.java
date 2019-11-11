package com.example.english;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.english.Model.vocab;
import com.example.english.db.appDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Basic_Mode extends AppCompatActivity {
    private ArrayList<vocab> vocab = new ArrayList<>();
    private appDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__mode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        db = appDB.getInstance(Basic_Mode.this);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                vocab = (ArrayList<vocab>) db.vocabDAO().getAllBasicVocab();
                Log.e("db",vocab.size()+"");
                next(0, vocab);



            }
        });
        t1.start();



    }
    public final void next(final int score,final ArrayList<vocab> db) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView score1 = findViewById(R.id.score);
                score1.setText(("Score : " + score));
            }
        });


        Random r1 = new Random();
        final int random = r1.nextInt(db.size());
        final TextView vocab = findViewById(R.id.vocab);
        Button choice1 = findViewById(R.id.choice1);
        Button choice2 = findViewById(R.id.choice2);
        Button choice3 = findViewById(R.id.choice3);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vocab.setText(db.get(random).vocab);
            }
        });

        int randomChoiceCorrect = r1.nextInt(3) + 1;
        int w1;
        int w2;
        if (randomChoiceCorrect == 1) {
            choice1.setText((db.get(random).mean));
            do {
                w1 = r1.nextInt(db.size());
                w2 = r1.nextInt(db.size());
            } while(w1 == w2 || w1 == random || w2== random);


            choice2.setText((db.get(w1)).mean);
            choice3.setText((db.get(w2)).mean);
        } else if (randomChoiceCorrect == 2) {
            choice2.setText(db.get(random).mean);
            do {
                w1 = r1.nextInt(db.size());
                w2 = r1.nextInt(db.size());
            } while(w1 == w2 || w1 == random || w2== random);


            choice1.setText((db.get(w1)).mean);
            choice3.setText((db.get(w2)).mean);
        } else {
            choice3.setText((db.get(random).mean));
            do {
                w1 = r1.nextInt(db.size());
                w2 = r1.nextInt(db.size());
            } while(w1 == w2 || w1 == random || w2== random);

            choice1.setText((db.get(w1)).mean);
            choice2.setText((db.get(w2)).mean);
        }

        if (randomChoiceCorrect == 1) {
            choice1.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    db.remove(random);
                    dialogTrue(score, db);
                }
            }));
            choice2.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);

                }
            }));
            choice3.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);

                }
            }));
        } else if (randomChoiceCorrect == 2) {
            choice2.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    db.remove(random);
                    dialogTrue(score, db);
                }
            }));
            choice1.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);

                }
            }));
            choice3.setOnClickListener((new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);
                }
            }));
        } else {
            choice3.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View it) {
                    db.remove(random);
                    dialogTrue(score, db);
                }
            });
            choice2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);
                }
            });
            choice1.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View it) {
                    dialogFalse(score, db, db.get(random).mean , random);
                }
            });
        }

    }
    public final void dialogTrue(int score2, ArrayList db) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(Basic_Mode.this);
        if (db.size() == 5) {
            alert.setTitle("End Game");
            final int score1 = score2 + 1;
            alert.setMessage("Your score = " + score1);
            alert.create().show();
            alert.setTitle("Result");
            alert.setMessage("Correct");
            alert.setIcon(R.drawable.correct);
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   Intent i1 = new Intent(Basic_Mode.this,result.class);
                   i1.putExtra("score",score1);
                    i1.putExtra("mode","basic");
                   startActivity(i1);
                   finish();

                }
            });
            alert.create().show();

        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    alert.setTitle("Result");
                    alert.setMessage("Correct");
                    alert.setIcon(R.drawable.correct);
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    });
                    alert.create().show();
                }
            });
            final int newscore  = score2+1;
            final  ArrayList newDB = db;
            final Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    next(newscore, newDB);


                }
            });
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    t1.start();

                }
            }, 300);


        }

    }

    public final void dialogFalse(final int score,ArrayList db, final String a, int index) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(Basic_Mode.this);
        db.remove(index);
        if (db.size() == 5) {
            alert.setTitle("Result");
            alert.setMessage(("Wrong, Answer = " + a));
            alert.setIcon(R.drawable.wrong);
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent i1 = new Intent(Basic_Mode.this,result.class);
                    i1.putExtra("score",score);
                    i1.putExtra("mode","basic");
                    startActivity(i1);
                    finish();
                }
            });
            alert.create().show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    alert.setTitle("Result");
                    alert.setMessage(("Wrong, Answer = " + a));
                    alert.setIcon(R.drawable.wrong);
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    });
                    alert.create().show();
                }
            });
            final int newscore  = score;
            final  ArrayList newDB = db;
            final Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    next(newscore , newDB);


                }
            });
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    t1.start();

                }
            }, 300);

        }

    }

}
