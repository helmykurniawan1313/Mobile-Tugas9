package com.example.tugas9

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database (entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao(): WordDao

    //mengatur room database
    private class  WordDataBaseCallBack (
        private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }
        //memberi efek tunda pada db
        suspend fun populateDatabase(wordDao: WordDao){
            wordDao.deleteALL()




        }
    }
    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        //memanggil database
        fun getDatabase(context: Context,
                        scope: CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,WordRoomDatabase::class.java,
                    "word_database")
                    .addCallback(WordDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}