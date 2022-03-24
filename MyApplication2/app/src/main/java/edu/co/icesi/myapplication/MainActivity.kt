package edu.co.icesi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var alfa: Button
    private lateinit var beta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alfa = findViewById(R.id.alfa)
        beta = findViewById(R.id.beta)

        alfa.setOnClickListener {
            beta.text = "Funciona!"
        }
    }
}