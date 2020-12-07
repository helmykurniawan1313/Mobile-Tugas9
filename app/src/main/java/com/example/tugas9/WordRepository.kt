package com.example.tugas9

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao){
    //Menampilkan semua data pada recycler view dan memberikan fungsi tunda
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
    //Memberikan fungsi tunda untuk delete
    suspend fun deleteALL(){
        wordDao.deleteALL()
    }
}
