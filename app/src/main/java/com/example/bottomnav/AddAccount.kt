package com.example.bottomnav
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.bottomnav.modelClasses.AccountModel
import com.google.firebase.database.*

class AddAccountActivity : AppCompatActivity() {

    private lateinit var etAccountName: EditText
    private lateinit var etInitialBalance: EditText
    private lateinit var etInitialBalanceDate: EditText
    private lateinit var spinnerAccountType: Spinner
    private lateinit var btnAddAccount: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_account__a_d_d)

        // Get ID of back button
        val returnButton = findViewById<ImageView>(R.id.imageButton3)
        // Back to Accounts Fragment
        returnButton.setOnClickListener {
            val intent = Intent(this,Accounts::class.java)
            startActivity(intent)
        }

        // Assign IDs and initialize values
        etAccountName = findViewById(R.id.editTextTextPersonName2)
        etInitialBalance = findViewById(R.id.editTextNumber)
        etInitialBalanceDate = findViewById(R.id.editTextDate)
        spinnerAccountType = findViewById(R.id.spinner2)
        btnAddAccount = findViewById(R.id.buttonaddacc)
        dbRef = FirebaseDatabase.getInstance().getReference("Accounts")

        // Set up spinner for account types
        ArrayAdapter.createFromResource(
            this,
            R.array.account_types_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAccountType.adapter = adapter
        }

        // Set up listener for add account button
        btnAddAccount.setOnClickListener {
            saveAccountData()
        }
    }

    private fun saveAccountData() {
        // Getting values
        val accountName = etAccountName.text.toString()
        val initialBalance = etInitialBalance.text.toString().toDouble()
        val initialBalanceDate = etInitialBalanceDate.text.toString()
        val accountType = spinnerAccountType.selectedItem.toString()

        // Creating new account object
        val accountId = dbRef.push().key!!
        val account = AccountModel(accountId, accountName, initialBalance, initialBalanceDate, accountType)

        // Saving account to database
        dbRef.child(accountId).setValue(account)
            .addOnCompleteListener {
                Toast.makeText(this, "Account added", Toast.LENGTH_LONG).show()

                // Clearing form fields
                etAccountName.text.clear()
                etInitialBalance.text.clear()
                etInitialBalanceDate.text.clear()
                spinnerAccountType.setSelection(0)
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}



