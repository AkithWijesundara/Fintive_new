package com.example.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addIncome : AppCompatActivity() {

    //Getting the views

    private lateinit var etIncomeName:EditText
    private lateinit var etIncomeAmount:EditText
    private lateinit var etIncomeDate:EditText
    private lateinit var etIncomeReccuring:RadioButton
    private lateinit var etIncomeNonReccuring:RadioButton
    private lateinit var etIncomeFrequancy:Spinner
    private lateinit var btnSaveData:Button

    //Database reference
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        //Intitializing
        etIncomeName=findViewById(R.id.incNameValue)
        etIncomeAmount=findViewById(R.id.incAmountValue)
        etIncomeDate=findViewById(R.id.incDateValue)
        etIncomeReccuring=findViewById(R.id.recurring)
        etIncomeNonReccuring=findViewById(R.id.nonrecurring)
        etIncomeFrequancy=findViewById(R.id.spinner)
        btnSaveData=findViewById(R.id.confirm)

        //Initializing
        dbRef=FirebaseDatabase.getInstance().getReference("Incomes")

        btnSaveData.setOnClickListener {
            saveIncomeData()
        }
    }

    private fun saveIncomeData(){
        //getting Values
        val incomeName=etIncomeName.text.toString()
        val amount=etIncomeAmount.text.toString()
        val incAmount: Double?=amount.toDouble()

    }
}