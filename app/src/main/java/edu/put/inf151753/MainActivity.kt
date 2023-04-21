package edu.put.inf151753

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun switchToDateActivity(){
        val myIntent = Intent(this, DateCalc::class.java)
        startActivity(myIntent)
    }

    fun switchToTimeActivity(){
        val myIntent = Intent(this, DateCalc::class.java)
        startActivity(myIntent)
    }
}