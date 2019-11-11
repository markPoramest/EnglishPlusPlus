package com.example.english.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.english.Model.vocab;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {vocab.class}, exportSchema = false, version = 1)
public abstract class appDB extends RoomDatabase {

    private static final String DB_NAME = "Expenditure.db";
    public abstract vocabDAO vocabDAO();
    private static appDB mInstance;
    public static synchronized appDB getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            appDB.class,
                            DB_NAME
                    )
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Moon", "พระจันทร์",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Sword", "ดาบ",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Knife", "มีด",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Ask", "ถาม",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Fail", "ตก",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Beast", "สัตว์ป่า",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Fly", "บิน",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Twist", "บิดเกลียว",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Check", "ตรวจสอบ",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Carry", "แบก",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Original", "ต้นฉบับ",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Soul", "วิญญาณ",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Mouse", "หนู",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Flow", "ลอย",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Soil", "ดิน",1));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Demure", "เคร่งขรึม",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Deride", "หัวเราะเยอะ",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Diligent", "ขยันหมั่นเพียร",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Elated", "น่ายินดี",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Embezzle", "ยักยอกเงิน",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Feral", "ดุร้าย",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Forsake", "ละทิ้ง",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Haughty", "ทระนง",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Impudent", "ทะลึ่ง",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Indolent", "ขี้เกียจ",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Inhibit", "ยับยั้ง",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Maverick", "นอกรีต",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Meticulous", "พิถีพิถัน",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Modicum", "จำนวนน้อย",2));
                                    mInstance.vocabDAO().insert_vocab(new vocab(0,"Alacrity", "กระตือรือร้น",2));

                                }
                            });
                        }
                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                        }
                    })
                    .build();
        }
        return mInstance;
    }
}
