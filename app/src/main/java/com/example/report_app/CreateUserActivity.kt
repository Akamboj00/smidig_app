package com.example.report_app

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.report_app.databuilders.Progress
import com.example.report_app.pages.LanguageActivity
import com.example.report_app.pages.LearnActivity
import com.example.report_app.pages.ReportActivity
import com.example.report_app.pages.UsersActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.referencecode.database.models.User
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.navigation_view.view.*

class CreateUserActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    var userId: Int=0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        runOnUiThread{
            setNavigation()

            createBtn.setOnClickListener{

                val firstName = firstNameInput.text.toString()
                val lastName = lastNameInput.text.toString()
                val language = languageInput.text.toString()
                writeNewUser(firstName, lastName, language)
            }
        }
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("userId")){
            userId = intent.getIntExtra("userId", 0)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setNavigation(){
        // Set Page Button Active
        navigation.profileBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.bright_blue))
        navigation.profileBtn.navigationProfileIcon.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(
                R.color.white
            )
        )
        navigation.profileBtn.profileText.setTextColor(resources.getColor(R.color.white))

        navigation.reportBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, ReportActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.learnBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, LearnActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.profileBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, UsersActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.languageBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, LanguageActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }

    @Override
    private fun writeNewUser(firstName: String, lastName: String, language: String){
        database = Firebase.database.reference
        val uid = Firebase.auth.uid.toString()

        val progressArray = ArrayList<Int>()
        val progressArray2 = ArrayList<Any>()

        val newUser = User("", null, "", "", "", progressArray2)

        if(intent.hasExtra("userId")){
            userId = intent.getIntExtra("userId", 0)
        }

        progressArray.add(0)
        progressArray.add(0)
        progressArray.add(0)
        progressArray.add(0)
        progressArray.add(0)
        progressArray.add(0)

        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)

        newUser.userId = userId.toLong()
        newUser.firstName = firstName
        newUser.lastName = lastName
        newUser.language = language
        newUser.id = uid
        newUser.progress = progressArray2

        database.child("users").child(newUser.id.toString()).child(newUser.userId.toString()).setValue(newUser)

        val postIntent = Intent(this.applicationContext, LanguageActivity::class.java)
        startActivity(postIntent)
        finish()
    }

    /*
    private fun generateUserId(){
        val uid = Firebase.auth.uid.toString()
        database.child("users").child(uid).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    userId = dataSnapshot.childrenCount.toInt()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

     */

}