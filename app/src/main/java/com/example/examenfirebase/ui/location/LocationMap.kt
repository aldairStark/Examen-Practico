package com.example.examenfirebase.ui.location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examenfirebase.R
import com.example.examenfirebase.components.MenuToolbar

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.ObjectInput
import java.io.ObjectStreamException

class LocationMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    private val lat = 19.3879161
    private val long = -99.1042885


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_map)

        CreateMap()

        var name:String?= intent?.getStringExtra("name")
        Toast.makeText(this,"$name",Toast.LENGTH_SHORT).show()
    }
    private fun CreateMap(){
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        var name:String?= intent?.getStringExtra("name")
        mMap = googleMap
        createMarkert(lat,long,name.toString())
    }

    private fun createMarkert(lat:Double,long:Double,Name:String) {

        val coordenates = LatLng(lat, long)
        val marker:MarkerOptions =MarkerOptions().position(coordenates).title(Name)
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenates, 18f),
            4000,
            null
        )

    }
}