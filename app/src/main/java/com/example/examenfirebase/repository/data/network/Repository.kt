package com.example.examenfirebase.repository.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.examenfirebase.retrofit.models.RecyclerViewClass.EmployData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Repository {
    private lateinit var database: DatabaseReference

    fun getEmployData():LiveData<MutableList<EmployData>>{

        val mutableData= MutableLiveData<MutableList<EmployData>>()
        database = Firebase.database.reference
       FirebaseDatabase.getInstance().getReference("data").child("employees").orderByChild("location/lat")
           .addValueEventListener(object : ValueEventListener{
           val listData = mutableListOf<EmployData>()
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                    for (h in snapshot.children){
                        val employData = h.getValue(EmployData::class.java)
                        employData?.let { listData.add(it) }
                    }
                    mutableData.value= listData

            }
        })
/*
        FirebaseFirestore.getInstance().collection("data").get().addOnSuccessListener { result ->
            val listData = mutableListOf<EmployData>()
                for (document in result){
                    val name:String? = document.getString("name")
                    val email:String? =document.getString("email")
                    val location:MutableList<String,String>=docume
                    val employData=EmployData(name.toString(),email.toString())
                }
        }*/
        return mutableData
    }
}
