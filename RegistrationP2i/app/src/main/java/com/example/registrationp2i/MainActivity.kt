package com.example.registrationp2i

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.coursesSpinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.courses_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val button: Button = findViewById(R.id.b1)
        button.setOnClickListener {
            val name: EditText = findViewById(R.id.e1)
            val selectedGenderId = findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
            val selectedGender = when (selectedGenderId) {
                R.id.radioButton -> "Male"
                R.id.radioButton2 -> "Female"
                R.id.radioButton3 -> "Others"
                else -> ""
            }

            val hobbies = StringBuilder()
            val checkBox1: CheckBox = findViewById(R.id.checkBox)
            val checkBox2: CheckBox = findViewById(R.id.checkBox2)
            if (checkBox1.isChecked) {
                hobbies.append(checkBox1.text).append(", ")
            }
            if (checkBox2.isChecked) {
                hobbies.append(checkBox2.text).append(", ")
            }

            val selectedCourse = spinner.selectedItem.toString()

            val message = "Name: ${name.text}\nGender: $selectedGender\nHobbies: $hobbies\nCourse: $selectedCourse"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
