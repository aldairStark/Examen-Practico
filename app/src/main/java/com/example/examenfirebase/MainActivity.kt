package com.example.examenfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.example.examenfirebase.components.MenuToolbar
import com.example.examenfirebase.ui.Registrer.EmployeesRegistrer
import com.example.examenfirebase.ui.interfaces.EmployeesRecyclerView
import com.example.examenfirebase.ui.interfaces.TestInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    MenuToolbar().Show(this,"Prueba Aldair",false)

        btnMyEmploy.setOnClickListener({
            ActionListEmploy()
        })
        btnRegistrerEmploy.setOnClickListener({
            ActionEmploy()
        })
        btnTest.setOnClickListener({
            ActionTest()
        })
    }
    private fun ActionEmploy(){
        startActivity(Intent(this,EmployeesRegistrer::class.java))

    }
    private fun ActionListEmploy(){
        startActivity(Intent(this,EmployeesRecyclerView::class.java))
    }
    private fun ActionTest(){
        startActivity(Intent(this,TestInterface::class.java))
    }
}