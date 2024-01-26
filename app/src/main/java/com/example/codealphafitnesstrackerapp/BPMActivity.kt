package com.example.codealphafitnesstrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BPMActivity : AppCompatActivity() {
    private lateinit var backBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpm)


        backBtn= findViewById(R.id.backBtn)

        backBtn.setOnClickListener {
            back()
        }

    }

    fun back(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}