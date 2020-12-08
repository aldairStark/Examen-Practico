package com.example.examenfirebase.ui.Registrer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.example.examenfirebase.common.MyApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registrer.*

class Registrer : AppCompatActivity() {
    private lateinit var txtNameUser:EditText
    private lateinit var txtLastNameUser:EditText
    private lateinit var txtEmailUser:EditText
    private lateinit var txtPasswordUser:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrer)

        txtNameUser=findViewById(R.id.edtName)
        txtLastNameUser=findViewById(R.id.edtUserLastName)
        txtEmailUser=findViewById(R.id.edtUserMail)
        txtPasswordUser=findViewById(R.id.edtUserPass)
        progressBar=ProgressBar(this)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("User")


        Btn_SendRegistrer.setOnClickListener({
            if (completedForm()){
                val intent =Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }

        })


    }
    fun registrer(view: View){

    }
   private fun createNewUser(){
       var name = txtNameUser.text.toString()
   }
    fun completedForm():Boolean{
        if (edtName.text.isNullOrBlank()) edtName.error = getString(R.string.fieldEmpty)
        if (edtUserLastName.text.isNullOrBlank()) edtUserLastName.error = getString(R.string.fieldEmpty)
        if (edtUserMail.text.isNullOrBlank()) edtUserMail.error = getString(R.string.fieldEmpty)
        if (edtUserPass.text.isNullOrBlank()) edtUserPass.error = getString(R.string.fieldEmpty)
        return !(edtName.text.isNullOrBlank() || edtUserLastName.text.isNullOrBlank() ||
                edtUserMail.text.isNullOrBlank() || edtUserPass.text.isNullOrBlank())
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Salir del registro")
                builder.setMessage("¿Confirmas salir del registro?")

                builder.setPositiveButton("Si") { dialog, which ->
                    onBackPressed()
                }

                builder.setNegativeButton("No") { dialog, which ->

                }
                builder.show()
                return true
            }

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}