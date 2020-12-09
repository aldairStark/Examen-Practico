package com.example.examenfirebase.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.example.examenfirebase.ui.Registrer.Registrer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registrer.*

class login : AppCompatActivity() {

    private  lateinit var txtUserEmail:EditText
    private lateinit var txtPassUser:EditText
    private lateinit var progresBar:ProgressBar
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtUserEmail = findViewById(R.id.Edt_User)
        txtPassUser= findViewById(R.id.EdtPassword)
        progresBar=findViewById(R.id.progressBarLogin)
        auth= FirebaseAuth.getInstance()


        Btn_Login.setOnClickListener({
          Loging()
        })
        btnRegistrer.setOnClickListener ({
                val intent= Intent(applicationContext,Registrer::class.java)
                startActivity(intent)
        })
    }
    fun completeForm():Boolean{
        if (Edt_User.text.isNullOrBlank()) Edt_User.error = getString(R.string.fieldEmpty)
        if (EdtPassword.text.isNullOrBlank()) EdtPassword.error = getString(R.string.fieldEmpty)
        return !(Edt_User.text.isNullOrBlank() || Edt_User.text.isNullOrBlank() ||
                EdtPassword.text.isNullOrBlank() || EdtPassword.text.isNullOrBlank())
    }
    private fun Loging(){
        val userEmail:String=txtUserEmail.text.toString()
        val userPass:String=txtPassUser.text.toString()


        if (completeForm()){
            progresBar.visibility= View.VISIBLE
            println(userEmail)
            println(userPass)
            auth.signInWithEmailAndPassword(userEmail,userPass)
                .addOnCompleteListener(this){
                task ->
                if (task.isSuccessful){
                    SuccessLogin()
                    progresBar.visibility=View.INVISIBLE
                }else{
                    Toast.makeText(this,"Error al ingresar",Toast.LENGTH_SHORT).show()
                    progresBar.visibility=View.INVISIBLE
                }
            }

        }
    }
    private fun SuccessLogin(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Salir del la app")
                builder.setMessage("¿Confirmas salir del la aplicacion?")

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