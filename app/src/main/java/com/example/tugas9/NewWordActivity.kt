package com.example.tugas9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewWordActivity : AppCompatActivity() {
    //Deklarasi Text Field
    private lateinit var editWordView: EditText
    val replyIntent = Intent()
    //Menghubungkan dengan activity_new_word.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        //Menghubungkan dengan Text Field
        editWordView = findViewById(R.id.edit_word)

        //Menghubungkan Button Save
        val button = findViewById<Button>(R.id.button_save)
        //Mengecek isian text field
        button.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
                Toast.makeText(
                    applicationContext,
                    "Data Tersimpan", Toast.LENGTH_LONG
                ).show()
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}