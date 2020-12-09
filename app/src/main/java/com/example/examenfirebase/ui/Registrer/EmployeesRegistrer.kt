package com.example.examenfirebase.ui.Registrer

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_employees_registrer.*


class EmployeesRegistrer : AppCompatActivity() {
    private lateinit var txtNameEmploy:EditText

    private lateinit var txtEmailEmploy:EditText

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var progressBar: ProgressBar
    private var maxid:Int = 0
    //location
    private  var lat:String ?=null
    private  var log:String ?=null
     private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    private  var PERMISSION_ID = 80


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_registrer)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        txtNameEmploy=findViewById(R.id.edtNameEmploy)
        txtEmailEmploy=findViewById(R.id.edtEmailEmploy)
        progressBar=findViewById(R.id.progressBarEmploy)

        database=FirebaseDatabase.getInstance()
        dbReference=database.reference.child("data")


        btnSendEmploy.setOnClickListener({
            getLastLocation()

            println("------------------------------------------")
            println(lat)
            println(log)
            println("------------------------------------------")
            if ( completeForm()){
                SendEmployees()

            }
        })
    }
    private fun SendEmployees(){
        getLastLocation()
        val employName:String=txtNameEmploy.text.toString()
        val employEmail=txtEmailEmploy.text.toString()
        if (completeForm()){
            println("****************************************************************")
            println(lat)
            println(log)
            println("****************************************************************")
            val location=Location(lat.toString(),log.toString())

             val ref =FirebaseDatabase.getInstance().getReference("data")
            val employId:Int = ref.push().hashCode()
            val employees = Employees(employId,employName,employEmail,location)

           ref.child("employees").child(employId.toString()).setValue(employees).addOnCompleteListener(this){
               task ->
               if (task.isComplete){
                   Toast.makeText(this,"DATOS GUARDADOS EXITOSAMENTE",Toast.LENGTH_SHORT).show()
                   ActionSendEmploy()
               }
           }


        }
    }
     fun getLastLocation(){
        if(checkPermission()){
            if (isLocationEnabled()){
                    fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){
                        task ->
                        var location:Location?= task.result
                        if (location == null){
                        }else{
                            println(location.latitude)
                            println(location.longitude)
                           lat = location.latitude.toString()
                           log = location.longitude.toString()

                        }
                    }
            }else {
                Toast.makeText(this, "Enciende tu servicio de localizacion", Toast.LENGTH_SHORT).show()
            }
        }else{
            RequestPermission()
        }

    }

    private fun ActionSendEmploy(){
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun completeForm():Boolean{

        if (txtNameEmploy.text.isNullOrBlank()) txtNameEmploy.error = getString(R.string.fieldEmpty)
        if (txtEmailEmploy.text.isNullOrBlank()) txtEmailEmploy.error = getString(R.string.fieldEmpty)
        return !(txtNameEmploy.text.isNullOrBlank() || txtEmailEmploy.text.isNullOrBlank())
    }
    private fun checkPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
             ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED  ){
         return true
        }
        return false
    }
    private fun RequestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ID)
    }
    private fun isLocationEnabled():Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }
}