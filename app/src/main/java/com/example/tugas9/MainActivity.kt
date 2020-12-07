package com.example.tugas9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var wordViewModel: WordViewModel
    private val newWordActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        //Menghubungkan dengan layout activity_main.xml agar dapat menampilkan konten
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Menghubungkan dengan recyeclerview
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let {
                adapter.setWords(it)
                //Memberi perintah saat data di klik yaitu dengan memberi toast
                adapter.setOnClickListener {
                    val current = words[it]
                    Toast.makeText(this, "Namanya "+ current.word , Toast.LENGTH_SHORT).show()
                }
            }

        })
        //Setting Floating Button Plus
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity,NewWordActivity::class.java)
            startActivityForResult(intent,newWordActivityRequestCode)

        }
        //Setting Button Hapus
        val del = findViewById<Button>(R.id.btnhapus)
        del.setOnClickListener{
            wordViewModel.deleteALL()
            Toast.makeText(applicationContext,
                R.string.Hapus, Toast.LENGTH_LONG).show()

        }

    }
    //Berfungsi membaca data yang telah dimasukkan
    override fun onActivityResult(requestCode: Int,     resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let   {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        }
        else {
            // Toast.makeText(applicationContext,
            // R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}