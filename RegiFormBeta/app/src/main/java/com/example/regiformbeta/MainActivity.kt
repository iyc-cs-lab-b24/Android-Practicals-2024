package com.example.regiformbeta


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the EditText view for DOB
        val dobEditText: EditText = findViewById(R.id.editTextText2)

        // Set a click listener to open the date picker dialog
        dobEditText.setOnClickListener {
            openDatePickerDialog()
        }
    }

    // Function to open DatePickerDialog
    private fun openDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Handle the selected date here
                val formattedDay = if (selectedDay < 10) "0$selectedDay" else "$selectedDay"
                val formattedMonth = if (selectedMonth < 9) "0${selectedMonth + 1}" else "${selectedMonth + 1}"
                val selectedDate = "$formattedDay/$formattedMonth/$selectedYear"
                // Update the EditText with the selected date
                val dobEditText: EditText = findViewById(R.id.editTextText2)
                dobEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    // Function to handle form submission
    fun submitForm(view: View) {
        // Get the name entered by the user
        val nameEditText: EditText = findViewById(R.id.editTextText)
        val userName = nameEditText.text.toString()

        // Display a Toast message with the user's name
        val toastMessage = "Hello $userName, your form submitted"
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
