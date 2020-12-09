package com.example.examenfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examenfirebase.ui.Registrer.EmployeesRegistrer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMyEmploy.setOnClickListener({
            ActionListEmploy()
        })
        btnRegistrerEmploy.setOnClickListener({
            ActionEmploy()
        })
    }
    private fun ActionEmploy(){
        startActivity(Intent(this,EmployeesRegistrer::class.java))
    }
    private fun ActionListEmploy(){
        startActivity(Intent(this,EmployeesRegistrer::class.java))
    }
}