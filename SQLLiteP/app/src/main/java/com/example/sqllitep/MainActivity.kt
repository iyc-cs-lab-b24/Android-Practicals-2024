package com.example.sqllitep

import android.app.AlertDialog
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: MyHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var editTextId: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextSubject: EditText
    private lateinit var editTextMarks: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyHelper(this)
        database = dbHelper.writableDatabase

        editTextId = findViewById(R.id.editTextId)
        editTextName = findViewById(R.id.editText)
        editTextSubject = findViewById(R.id.editText2)
        editTextMarks = findViewById(R.id.editText3)

        val addButton: Button = findViewById(R.id.button)
        addButton.setOnClickListener {
            addData()
        }

        val viewAllButton: Button = findViewById(R.id.button2)
        viewAllButton.setOnClickListener {
            viewAll()
        }

        val updateButton: Button = findViewById(R.id.button_update)
        updateButton.setOnClickListener {
            updateData()
        }

        val deleteButton: Button = findViewById(R.id.Delete)
        deleteButton.setOnClickListener {
            deleteData()
        }
    }

    private fun addData() {
        val id = editTextId.text.toString()
        val name = editTextName.text.toString()
        val subject = editTextSubject.text.toString()
        val marks = editTextMarks.text.toString()

        val contentValues = ContentValues()
        contentValues.put(MyHelper.COLUMN_ID, id)
        contentValues.put(MyHelper.COLUMN_NAME, name)
        contentValues.put(MyHelper.COLUMN_SUBJECT, subject)
        contentValues.put(MyHelper.COLUMN_MARKS, marks)

        val result = database.insert(MyHelper.TABLE_NAME, null, contentValues)
        if (result == -1L) {
            Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewAll() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM ${MyHelper.TABLE_NAME}", null)
        val stringBuilder = StringBuilder()
        while (cursor.moveToNext()) {
            stringBuilder.append("ID: ${cursor.getString(0)}, Name: ${cursor.getString(1)}, " +
                    "Subject: ${cursor.getString(2)}, Marks: ${cursor.getString(3)}\n")
        }
        showDataDialog(stringBuilder.toString())
    }

    private fun updateData() {
        val id = editTextId.text.toString()
        val name = editTextName.text.toString()
        val subject = editTextSubject.text.toString()
        val marks = editTextMarks.text.toString()

        val contentValues = ContentValues()
        contentValues.put(MyHelper.COLUMN_NAME, name)
        contentValues.put(MyHelper.COLUMN_SUBJECT, subject)
        contentValues.put(MyHelper.COLUMN_MARKS, marks)

        val result = database.update(MyHelper.TABLE_NAME, contentValues, "${MyHelper.COLUMN_ID} = ?", arrayOf(id))
        if (result > 0) {
            Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData() {
        val id = editTextId.text.toString()
        val result = database.delete(MyHelper.TABLE_NAME, "${MyHelper.COLUMN_ID} = ?", arrayOf(id))
        if (result > 0) {
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDataDialog(data: String) {
        val formattedData = data.replace(", ", "\n") // Add a new line after each attribute
        val formattedDataWithBlankLine = formattedData.replace(Regex("(Marks:.*)\n(?=ID:|$)"), "$1\n\n") // Add a blank line between each person's data
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Stored Data")
        builder.setMessage(formattedDataWithBlankLine)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }



    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }
}
