package com.android.spinnertde

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etDay: EditText = findViewById(R.id.etDay)
        val etYear: EditText = findViewById(R.id.etYear)
        val spinnerMonth: Spinner = findViewById(R.id.spinnerMonth)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)
        val tvResult: TextView = findViewById(R.id.tvResult)

        val months = resources.getStringArray(R.array.months_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMonth.adapter = adapter

        btnCalculate.setOnClickListener {
            val day = etDay.text.toString().toIntOrNull()
            val year = etYear.text.toString().toIntOrNull()
            val month = spinnerMonth.selectedItemPosition

            if (day != null && year != null && day in 1..31) {
                val age = calculateAge(day, month + 1, year)
                tvResult.text = "Sua idade é $age anos"
            } else {
                Toast.makeText(this, "Por favor, insira valores válidos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateAge(day: Int, month: Int, year: Int): Int {
        val today = Calendar.getInstance()
        val birthDate = Calendar.getInstance()
        birthDate.set(year, month - 1, day)

        var age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }
}
