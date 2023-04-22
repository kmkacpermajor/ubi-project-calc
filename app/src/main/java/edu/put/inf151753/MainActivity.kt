package edu.put.inf151753

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeToTime(view: View){
        startActivity(Intent(this, TimeCalc::class.java))
    }

    fun changeToDate(view: View){
        startActivity(Intent(this, DateCalc::class.java))
    }
}