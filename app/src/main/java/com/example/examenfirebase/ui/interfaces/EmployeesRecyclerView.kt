package com.example.examenfirebase.ui.interfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfirebase.R
import com.example.examenfirebase.ui.viewModel.EmployeesViewModel
import com.example.examenfirebase.ui.Employees.RecyclerAdapterEmployess
import kotlinx.android.synthetic.main.activity_employees_recycler_view.*


class EmployeesRecyclerView : AppCompatActivity() {
        private lateinit var adapter: RecyclerAdapterEmployess
    private val  ViewModel by lazy {ViewModelProvider(this).get(EmployeesViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_recycler_view)
        adapter =
            RecyclerAdapterEmployess(this)
        rcyEmployees.layoutManager = LinearLayoutManager(this)
        rcyEmployees.adapter=adapter
        observerData()
/*
val dummyList= mutableListOf<EmployData>()
dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
        dummyList.add(EmployData("aldair","goninava"))
     adapter.setListData(dummyList)
     adapter.notifyDataSetChanged()
 */
    }
    fun observerData(){
        ViewModel.fetchEmployeesData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}