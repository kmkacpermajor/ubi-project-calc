package edu.put.inf151753

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class TimeCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_calc)
    }

    fun goBack(view: View){
        this.finish()
    }

    fun clearBottom(view: View){
        val hour = findViewById<EditText>(R.id.hourBottom)
        val minute = findViewById<EditText>(R.id.minuteBottom)
        val second = findViewById<EditText>(R.id.secondBottom)

        hour.setText("0")
        minute.setText("0")
        second.setText("0")
    }
}