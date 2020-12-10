package com.example.examenfirebase.components

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import com.example.examenfirebase.MainActivity
import com.example.examenfirebase.R
import kotlinx.android.synthetic.main.action_bar_toolbar.view.*

class MenuToolbar {
    fun Show(activities: AppCompatActivity, title:String,upButton:Boolean){
        activities.setSupportActionBar(activities.findViewById(R.id.tb_Menu))
        activities.supportActionBar?.title=title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}