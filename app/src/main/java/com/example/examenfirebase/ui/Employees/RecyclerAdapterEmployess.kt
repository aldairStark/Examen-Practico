package com.example.examenfirebase.ui.Employees

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfirebase.R
import com.example.examenfirebase.retrofit.models.RecyclerViewClass.EmployData
import com.example.examenfirebase.retrofit.models.RecyclerViewClass.LocationData
import kotlinx.android.synthetic.main.item_employees_view.view.*

class RecyclerAdapterEmployess(private val context:Context,private val onItemClick:OnClickItem):RecyclerView.Adapter<RecyclerAdapterEmployess.EmployViewHolder>() {
private var dataList = mutableListOf<EmployData>()
    private var dataLocat = mutableListOf<LocationData>()

fun sentLocation(data: MutableList<LocationData>){
    dataLocat=data
}


fun setListData(data:MutableList<EmployData> ){
    dataList=data

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_employees_view,parent,false)
        return EmployViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataList.size >0){
            return dataList.size
        }else
            return 0
    }

    override fun onBindViewHolder(holder: EmployViewHolder, position: Int) {

     val employData:EmployData = dataList[position]
        holder.bindView(employData)

    }

    inner class EmployViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindView(employData: EmployData){



            itemView.tvwNameEmploy.text=employData.name
            itemView.tvwEmailEmploy.text=employData.mail
            /*
          var lat = employData.location.log
           var log = employData.location.lat
           */
            var name=employData.name.toString()
            var mail=employData.name.toString()
            itemView.setOnClickListener {
                onItemClick.onItemClick(name,mail)
            }

            println("$%$%&$%$&$%$%$&$%$%$%&$%&%$%$%$%$&%$%$%$%$&%$$$%$%$%$%$%$%$%$%$%$%%$%$%$%%$")
            println(itemView.tvwNameEmploy.text)
          println(employData)
            println("$%$%&$%$&$%$%$&$%$%$%&$%&%$%$%$%$&%$%$%$%$&%$$$%$%$%$%$%$%$%$%$%$%%$%$%$%%$")
        } fun bindLocation(employloc: LocationData){

                itemView.setOnClickListener {
                    onItemClick.onItemClick("","")
                }
        }

    }
    interface OnClickItem {
        fun onItemClick(name:String,email:String)
    }
    }


