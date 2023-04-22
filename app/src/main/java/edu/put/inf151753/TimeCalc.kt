package edu.put.inf151753

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class TimeCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_calc)
    }

    fun goBack(view: View) {
        this.finish()
    }

    fun addTime(view: View) {
        val hourTopView = findViewById<EditText>(R.id.hourTop)
        val minuteTopView = findViewById<EditText>(R.id.minuteTop)
        val secondTopView = findViewById<EditText>(R.id.secondTop)

        val hourBottomView = findViewById<EditText>(R.id.hourBottom)
        val minuteBottomView = findViewById<EditText>(R.id.minuteBottom)
        val secondBottomView = findViewById<EditText>(R.id.secondBottom)

        var hourTop: Int = hourTopView.text.toString().toInt()
        var minuteTop: Int = minuteTopView.text.toString().toInt()
        var secondTop: Int = secondTopView.text.toString().toInt()

        val hourBottom: Int = hourBottomView.text.toString().toInt()
        val minuteBottom: Int = minuteBottomView.text.toString().toInt()
        val secondBottom: Int = secondBottomView.text.toString().toInt()

        secondTop += secondBottom
        while (secondTop >= 60){
            minuteTop++
            secondTop -= 60
        }

        minuteTop += minuteBottom
        while (minuteTop >= 60){
            hourTop++
            minuteTop -= 60
        }

        hourTop += hourBottom

        hourTopView.setText(hourTop.toString())
        minuteTopView.setText(minuteTop.toString())
        secondTopView.setText(secondTop.toString())
        clearBottom(view)
    }

    fun subtractTime(view: View) {
        val hourTopView = findViewById<EditText>(R.id.hourTop)
        val minuteTopView = findViewById<EditText>(R.id.minuteTop)
        val secondTopView = findViewById<EditText>(R.id.secondTop)

        val hourBottomView = findViewById<EditText>(R.id.hourBottom)
        val minuteBottomView = findViewById<EditText>(R.id.minuteBottom)
        val secondBottomView = findViewById<EditText>(R.id.secondBottom)

        var hourTop: Int = hourTopView.text.toString().toInt()
        var minuteTop: Int = minuteTopView.text.toString().toInt()
        var secondTop: Int = secondTopView.text.toString().toInt()

        val hourBottom: Int = hourBottomView.text.toString().toInt()
        val minuteBottom: Int = minuteBottomView.text.toString().toInt()
        val secondBottom: Int = secondBottomView.text.toString().toInt()

        secondTop -= secondBottom
        while (secondTop < 0){
            --minuteTop
            secondTop += 60
        }


        minuteTop -= minuteBottom
        while (minuteTop < 0){
            --hourTop
            minuteTop += 60
        }

        hourTop -= hourBottom

        hourTopView.setText(hourTop.toString())
        minuteTopView.setText(minuteTop.toString())
        secondTopView.setText(secondTop.toString())
        clearBottom(view)
    }

    fun clearTop(view: View) {
        val hour = findViewById<EditText>(R.id.hourTop)
        val minute = findViewById<EditText>(R.id.minuteTop)
        val second = findViewById<EditText>(R.id.secondTop)

        hour.setText("0")
        minute.setText("0")
        second.setText("0")
    }

    fun clearBottom(view: View) {
        val hour = findViewById<EditText>(R.id.hourBottom)
        val minute = findViewById<EditText>(R.id.minuteBottom)
        val second = findViewById<EditText>(R.id.secondBottom)

        hour.setText("0")
        minute.setText("0")
        second.setText("0")
    }

    fun clearBoth(view: View) {
        clearTop(view)
        clearBottom(view)
    }
}