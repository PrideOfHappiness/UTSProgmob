package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnGet : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGet = findViewById(R.id.btnGet)

        btnGet.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@MainActivity, GetActivity::class.java)
            startActivity(intent)
        })
    }
}