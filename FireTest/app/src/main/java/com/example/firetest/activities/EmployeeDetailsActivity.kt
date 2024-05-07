package com.example.firetest.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firetest.R
import com.example.firetest.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpAge: TextView
    private lateinit var tvEmpSalary: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            val oldId = intent.getStringExtra("empId").toString()
            val empName = intent.getStringExtra("empName").toString()
            openUpdateDialog(oldId, empName)
        }

        btnDelete.setOnClickListener {
            val empId = intent.getStringExtra("empId").toString()
            deleteRecord(empId)
        }

    }

    private fun initView() {
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpAge = findViewById(R.id.tvEmpAge)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpAge.text = intent.getStringExtra("empAge")
        tvEmpSalary.text = intent.getStringExtra("empSalary")
    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Error: ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        oldId: String,
        empName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etEmpId = mDialogView.findViewById<EditText>(R.id.etEmpId)
        val etEmpName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val etEmpAge = mDialogView.findViewById<EditText>(R.id.etEmpAge)
        val etEmpSalary = mDialogView.findViewById<EditText>(R.id.etEmpSalary)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpId.setText(oldId)
        etEmpName.setText(intent.getStringExtra("empName").toString())
        etEmpAge.setText(intent.getStringExtra("empAge").toString())
        etEmpSalary.setText(intent.getStringExtra("empSalary").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            val newId = etEmpId.text.toString()
            val name = etEmpName.text.toString()
            val age = etEmpAge.text.toString()
            val salary = etEmpSalary.text.toString()

            updateEmpData(oldId, newId, name, age, salary)

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            // Update text views with new data
            tvEmpId.text = newId
            tvEmpName.text = name
            tvEmpAge.text = age
            tvEmpSalary.text = salary

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        oldId: String,
        newId: String,
        name: String,
        age: String,
        salary: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        // Retrieve existing data with old ID
        dbRef.child(oldId).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // Get existing employee data
                val existingEmployee = snapshot.getValue(EmployeeModel::class.java)

                // Delete existing data with old ID
                dbRef.child(oldId).removeValue().addOnSuccessListener {
                    // Save updated data with new ID
                    dbRef.child(newId).setValue(existingEmployee).addOnSuccessListener {
                        // Update employee details
                        val updatedEmployee = EmployeeModel(newId, name, age, salary)
                        dbRef.child(newId).setValue(updatedEmployee)
                    }
                }
            }
        }
    }
}
