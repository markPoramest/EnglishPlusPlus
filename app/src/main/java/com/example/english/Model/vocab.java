package com.example.english.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocab")
public class vocab {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "vocab")
    public String vocab;

    @ColumnInfo(name = "mean")
    public String mean;

    @ColumnInfo(name = "difficult")
    public int difficult;

    public vocab(int id,String vocab, String mean, int difficult) {
        this.id= id;
        this.vocab = vocab;
        this.mean = mean;
        this.difficult = difficult;
    }

}
