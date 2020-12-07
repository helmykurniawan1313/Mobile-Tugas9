package com.example.tugas9

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel (application: Application) : AndroidViewModel(application){
    //deklarasi variabel
    private val repository: WordRepository
    val allWords : LiveData<List<Word>>
    //mengambil data dari room database
    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    //fungsi memasukkan kata
    fun insert(word:Word) = viewModelScope.launch {
        repository.insert(word)
    }
    //fungsi menghapus data
    fun deleteALL() = viewModelScope.launch {
        repository.deleteALL()
    }
}