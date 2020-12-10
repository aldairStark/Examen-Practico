package com.example.examenfirebase.ui.interfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfirebase.R
import com.example.examenfirebase.components.MenuToolbar
import com.example.examenfirebase.ui.viewModel.EmployeesViewModel
import com.example.examenfirebase.ui.Employees.RecyclerAdapterEmployess
import com.example.examenfirebase.ui.location.LocationMap
import kotlinx.android.synthetic.main.activity_employees_recycler_view.*


class EmployeesRecyclerView : AppCompatActivity(),RecyclerAdapterEmployess.OnClickItem {
        private lateinit var adapter: RecyclerAdapterEmployess
    private val  ViewModel by lazy {ViewModelProvider(this).get(EmployeesViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_recycler_view)


        MenuToolbar().Show(this,"Listado Coolaboradores",true)
        adapter =
            RecyclerAdapterEmployess(this,this)
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
        shimmer_view_container.startShimmer()
        ViewModel.fetchEmployeesData().observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.hideShimmer()
            shimmer_view_container.visibility= View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(name:String, email: String) {
        val intent= Intent(this, LocationMap::class.java)

        print("EGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTRER")
        print(name)
        print(email)
        print("EGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTREREGISTRER")
Toast.makeText(this,"$name",Toast.LENGTH_SHORT).show()
        intent.putExtra("name",name)
        intent.putExtra("mail",email)
        startActivity(intent)
    }
}