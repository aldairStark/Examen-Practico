package com.example.examenfirebase.ui.Registrer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.EditText
import android.widget.ProgressBar
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_employees_registrer.*
import kotlinx.android.synthetic.main.activity_registrer.*
import java.util.concurrent.CountDownLatch

class EmployeesRegistrer : AppCompatActivity() {
    private lateinit var txtNameEmploy:EditText

    private lateinit var txtEmailEmploy:EditText
    private lateinit var lat:String
    private lateinit var log:String
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_registrer)
        txtNameEmploy=findViewById(R.id.edtNameEmploy)
        txtEmailEmploy=findViewById(R.id.edtEmailEmploy)
        progressBar=findViewById(R.id.progressBarEmploy)

        database=FirebaseDatabase.getInstance()
        dbReference=database.reference.child("data")

        btnSendEmploy.setOnClickListener({
            if ( completeForm()){
               ActionSendEmploy()
            }
        })
    }
    private fun ActionSendEmploy(){
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun completeForm():Boolean{

        if (txtNameEmploy.text.isNullOrBlank()) txtNameEmploy.error = getString(R.string.fieldEmpty)
        if (txtEmailEmploy.text.isNullOrBlank()) txtEmailEmploy.error = getString(R.string.fieldEmpty)
        return !(txtNameEmploy.text.isNullOrBlank() || txtEmailEmploy.text.isNullOrBlank())
    }
}