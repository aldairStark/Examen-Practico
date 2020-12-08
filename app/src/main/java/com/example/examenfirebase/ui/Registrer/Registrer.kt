package com.example.examenfirebase.ui.Registrer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.example.examenfirebase.common.MyApp
import com.example.examenfirebase.ui.Login.login
import com.google.android.gms.dynamic.IFragmentWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
        progressBar=findViewById(R.id.prgB_registrer)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("User")



        Btn_SendRegistrer.setOnClickListener({
            if (completedForm()){
                createNewUser()
            }

        })


    }
   private fun createNewUser(){
       val name = txtNameUser.text.toString()
       val lastname = txtLastNameUser.text.toString()
       val email = txtEmailUser.text.toString()
       val password = txtPasswordUser.text.toString()

       if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) ){
            progressBar.visibility= View.VISIBLE

           auth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(this){
                   task ->
                   if (task.isComplete){
                       val user:FirebaseUser?=auth.currentUser
                       SendEmailVerify(user)
                       val userBD=dbReference.child(user!!.uid)
                       userBD.child("Name").setValue(name)
                       userBD.child("LastName").setValue(name)
                       ActionRegistrer()
                   }
               }
       }
   }
    private fun ActionRegistrer(){
        startActivity(Intent(MyApp.instance,login::class.java))
    }
    private fun SendEmailVerify(user: FirebaseUser?){
            user?.sendEmailVerification()?.addOnCompleteListener(this){
                task ->
                if (task.isComplete){
                    Toast.makeText(MyApp.instance,"Email enviado correctamente",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(MyApp.instance,"Error al enviar Email",Toast.LENGTH_SHORT).show()
                }
            }
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