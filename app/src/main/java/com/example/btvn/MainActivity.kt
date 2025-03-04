package com.example.btvn

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnImg1 = findViewById<ImageView>(R.id.imageButton2)
        val btnImg2 = findViewById<ImageView>(R.id.imageButton3)
        val img = findViewById<ImageView>(R.id.imageView2)
        val text1 = findViewById<TextView>(R.id.textView3)
        val  text2 = findViewById<TextView>(R.id.textView4)


        img.setImageBitmap(null);
        btnImg1.setImageResource(R.drawable.arrow_lefft)
        btnImg2.setImageResource(R.drawable.book)
        img.setImageResource(R.drawable.chandung)
        img.setBackground(null)

        text1.setText("Jonny Dark")
        text2.setText("Amenica")


    }

}