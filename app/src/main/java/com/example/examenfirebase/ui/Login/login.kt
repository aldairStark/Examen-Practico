package com.example.examenfirebase.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.example.examenfirebase.ui.Registrer.Registrer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registrer.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Btn_Login.setOnClickListener({
            if (completeForm()){
                val intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
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