package com.example.english.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.english.Model.vocab;

import java.util.List;

@Dao
public interface vocabDAO {
    @Query("Select * from  vocab where difficult = 1")
    List<vocab> getAllBasicVocab();

    @Query("Select * from  vocab where difficult = 2")
    List<vocab> getAlladvVocab();

    @Insert
    void insert_vocab(vocab vocab);


}
