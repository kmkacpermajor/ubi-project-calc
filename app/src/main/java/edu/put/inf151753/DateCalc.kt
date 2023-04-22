package edu.put.inf151753

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit


class DateCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_calc)

        val topDatePicker = findViewById<DatePicker>(R.id.topDatePicker)
        val bottomDatePicker = findViewById<DatePicker>(R.id.bottomDatePicker)

        topDatePicker.setOnDateChangedListener{
            _, _, _, _ -> dateChangedHandler()
        }

        bottomDatePicker.setOnDateChangedListener{
                _, _, _, _ -> dateChangedHandler()
        }
    }

    fun dateChangedHandler(){
        calculateDayDifference()
        calculateWorkingDayDifference()
    }

    fun calculateDayDifference(){
        val topDatePicker = findViewById<DatePicker>(R.id.topDatePicker)
        val bottomDatePicker = findViewById<DatePicker>(R.id.bottomDatePicker)

        val daysBetweenView = findViewById<EditText>(R.id.daysBetweenValue)

        val topDate = LocalDate.of(topDatePicker.year, topDatePicker.month, topDatePicker.dayOfMonth)
        val bottomDate = LocalDate.of(bottomDatePicker.year, bottomDatePicker.month, bottomDatePicker.dayOfMonth)

        daysBetweenView.setText(ChronoUnit.DAYS.between(topDate, bottomDate).toString())
    }

    fun calculateEaster(year: Int): LocalDate{
        if (year == 2018){
            return LocalDate.of(2018, Month.NOVEMBER, 12)
        }

        if(year == 2005){
            return LocalDate.of(2005, Month.APRIL, 8)
        }

        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val p = (h + l - 7 * m + 114) % 31

        val day = p + 1
        val month = (h + l - 7 * m + 114) / 31

        return LocalDate.of(year, month, day)
    }

    fun getHolidaysBetween(first: LocalDate, second: LocalDate): List<LocalDate>{
        val years = (first.year.. second.year).toList()

        val holidays = mutableListOf<LocalDate>()

        for (year in years){
            holidays.add(LocalDate.of(year, Month.JANUARY, 1))
            holidays.add(LocalDate.of(year, Month.JANUARY, 6))
            holidays.add(LocalDate.of(year, Month.MAY, 1))
            holidays.add(LocalDate.of(year, Month.MAY, 3))
            holidays.add(LocalDate.of(year, Month.AUGUST, 15))
            holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
            holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
            holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
            holidays.add(LocalDate.of(year, Month.DECEMBER, 26))

            val easter = calculateEaster(year)
            holidays.add(easter)
            holidays.add(easter.plusDays(1))
            holidays.add(easter.plusDays(60))
        }

        return holidays.toList()
    }
    fun getWorkingDaysBetween(first: LocalDate, second: LocalDate): Int{
        val workingDates = mutableListOf<LocalDate>()
        val holidays = getHolidaysBetween(first, second)
        var current = first
        while(current != second){
            if (current.dayOfWeek != DayOfWeek.SATURDAY
                && current.dayOfWeek != DayOfWeek.SUNDAY
                && !holidays.contains(current)) {

                workingDates.add(current)
            }
            current = current.plusDays(1)
        }

        return workingDates.count()
    }

    fun calculateWorkingDayDifference() {
        val topDatePicker = findViewById<DatePicker>(R.id.topDatePicker)
        val bottomDatePicker = findViewById<DatePicker>(R.id.bottomDatePicker)

        val workingDaysBetweenView = findViewById<TextView>(R.id.workingDaysBetweenValue)

        val topDate =
            LocalDate.of(topDatePicker.year, topDatePicker.month+1, topDatePicker.dayOfMonth)
        val bottomDate =
            LocalDate.of(bottomDatePicker.year, bottomDatePicker.month+1, bottomDatePicker.dayOfMonth)

        workingDaysBetweenView.text = getWorkingDaysBetween(topDate, bottomDate).toString()
    }


    fun addDaysToBottom(view: View){
        val bottomDatePicker = findViewById<DatePicker>(R.id.bottomDatePicker)
        val daysBetweenView = findViewById<EditText>(R.id.daysBetweenValue)

        var date = LocalDate.of(bottomDatePicker.year, bottomDatePicker.month, bottomDatePicker.dayOfMonth)
        date = date.plusDays(daysBetweenView.text.toString().toLong())

        bottomDatePicker.updateDate(date.year, date.monthValue, date.dayOfMonth)
    }

    fun goBack(view: View){
        this.finish()
    }
}