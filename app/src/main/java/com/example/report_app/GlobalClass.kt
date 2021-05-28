package com.example.report_app

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.referencecode.database.models.User

class GlobalClass : AppCompatActivity() {
    companion object{
        var user: User?=null
    }
}
