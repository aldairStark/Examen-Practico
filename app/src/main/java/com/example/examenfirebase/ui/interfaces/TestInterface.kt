package com.example.examenfirebase.ui.interfaces

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.examenfirebase.R
import com.example.examenfirebase.api.FieldViewModel
import com.example.examenfirebase.api.repository.MainViewModelFactory
import com.example.examenfirebase.api.repository.RepositoryField
import com.example.examenfirebase.common.Constants
import com.example.examenfirebase.components.MenuToolbar
import com.example.examenfirebase.retrofit.RestAdapter
import com.example.examenfirebase.retrofit.ApiService
import com.example.examenfirebase.retrofit.models.Data
import kotlinx.android.synthetic.main.activity_test_interface.*
import retrofit2.*

class TestInterface : AppCompatActivity() {
        private lateinit var  viewModel:FieldViewModel
    var mydownloadid:Long= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_interface)
        MenuToolbar().Show(this,"Consumo Api", true)
        //getCurrentData()
      /*  val repository = RepositoryField()
        val viewModelFactory =MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FieldViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {responce->
            if(responce.isExecuted){
                Log.i("RESPONSE" , responce. .file.toString())
                tvwResponse.text=responce.body()?.file!!

            }else{
               Log.i("ERROR",responce.errorBody().toString())
                tvwResponse.text=responce.code().toString()
            }

        })*/
        btnDowload.setOnClickListener{
            var request = DownloadManager.Request(
                    Uri.parse(Constants.FILE_URL))
                    .setTitle("Archivo JSon")
                    .setDescription("Archivo Json Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

                    .setAllowedOverMetered(true)
            var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
           mydownloadid= dm.enqueue(request)
        }
        var br = object :BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
      var id=intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)
                if (id == mydownloadid){
                    Toast.makeText(this@TestInterface,"Descarga completa",Toast.LENGTH_SHORT).show()
                }
            }

        }
        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        btnApiRest.setOnClickListener { v ->
            progressBar.visibility=View.VISIBLE
            callServices()
               // getCurrentData()
        }
    }


    private fun callServices() {
        val userService: ApiService = RestAdapter.getRestAdapter().create(ApiService::class.java)
        val result: Call<Data> = userService.downloadFileWithFixedUrl()

        result.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {

                Toast.makeText(this@TestInterface,"Error",Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                if (response.isSuccessful){
                    progressBar.visibility=View.GONE
                    btnApiRest.visibility=View.GONE
                    Toast.makeText(this@TestInterface,"Conexion Exitosa",Toast.LENGTH_SHORT).show()
                    btnDowload.visibility= View.VISIBLE
                    Log.i("Field", response.body()?.file.toString())
                    var name = response.body()?.file.toString()

                    tvwResponse.text=Constants.FILE_URL
                }else{
                    Log.i("Response",response.code().toString())
                }


          }
        })
    }

}