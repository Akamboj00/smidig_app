package com.example.report_app.pages

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.eksamen.pgr208.adaptors.UserRecyclerAdapter
import com.eksamen.pgr208.adaptors.UserTemplate
import com.example.report_app.CreateUserActivity
import com.example.report_app.R
import com.example.report_app.databuilders.Progress
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.referencecode.database.models.Report
import com.google.firebase.referencecode.database.models.User
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.navigation_view.view.*


class UsersActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private val TAG = "ReadAndWriteSnippets"

    //var user =  User("", "", "", "", "",)
    var report =  Report("", "", null, "", "", "")

    var userId: Int=0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        runOnUiThread {

            userTestBtn.setOnClickListener{
                writeNewUser("Maximilian", "Rasmussen", "SWE")
                writeNewUser("Oscar", "Torgersen", "NB")
                writeNewUser("Edvard", "Leirflaten", "NB")
                /*
                writeNewUser("Simen", "Mandt", "NB")
                writeNewUser("Philip", "Helgesen", "AF")
                writeNewUser("Abhilash", "Kamboj", "DA")
                writeNewUser("Harald", "Sigvartsen", "EN")
                 */

                //reportNewRepair("0", "3", "6", "Replaced a part that was damaged.")
            }
            newUserBtn.setOnClickListener {
                database = Firebase.database.reference
                val uid = Firebase.auth.uid.toString()
                val intent = Intent(applicationContext, CreateUserActivity()::class.java)

                database.child("users").child(uid).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            intent.putExtra("userId", dataSnapshot.childrenCount.toInt())
                            startActivity(intent)
                        } else {
                            intent.putExtra("userId", 0)
                            startActivity(intent)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
            }

            /*
            Firebase.database.reference.child("users").child(Firebase.auth.uid.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    /*
                    val users: MutableList<Any?> = ArrayList()

                    for (ds in dataSnapshot.children) {
                        val data = ds.value
                        users.add(data)
                    }

                    Log.i("TAG", "$users")
                      */

                    dataSnapshot.children.forEach { child ->
                        // Extract Message object from the DataSnapshot
                        val usr: User? = child.getValue<User>()

                        // Use the message
                        // [START_EXCLUDE]
                        Log.d(TAG, "message text: ${usr?.id}")
                        Log.d(TAG, "message sender name: ${usr?.firstName}")
                        // [END_EXCLUDE]
                    }


                    val dataList: ArrayList<User> = ArrayList()
                    for (ds in dataSnapshot.children) {
                        val data = ds.value
                        dataList.add(data as User)
                    }

                    val users = ArrayList<User>()

                    for (data in dataList){
                        users.add(User(data.firstName))
                    }

                    println("$dataList RRRRRRRRRRRRRRRRRRRR")
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

             */


            setNavigation()
            var arrayList = ArrayList<UserTemplate>()

            val user1 = UserTemplate("Maximilian", "Rasmussen", 1)
            val user2 = UserTemplate("User2", "User2LastName", 2)
            val user3 = UserTemplate("User3", "User3LastName", 3)

            arrayList.add(user1)
            arrayList.add(user2)
            arrayList.add(user3)

            userRecycler.layoutManager = LinearLayoutManager(this)
            userRecycler.adapter = UserRecyclerAdapter(arrayList)
        }
    }

    @Override
    private fun writeNewUser(firstName: String, lastName: String, language: String){
        database = Firebase.database.reference
        val uid = Firebase.auth.uid.toString()

        val progressArray = ArrayList<Int>()
        val progressArray2 = ArrayList<Any>()

        val newUser = User("", "", "", "", "", progressArray2)
        //generateUserId()

        /*
        database.child("users").child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    newUser.userId = dataSnapshot.childrenCount.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
         */

        val newProgress = Progress()
        progressArray.add(0)
        progressArray.add(0)
        progressArray.add(1)
        progressArray.add(1)
        progressArray.add(0)
        progressArray.add(0)

        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)
        progressArray2.add(progressArray)


        newUser.userId = userId.toString()
        newUser.firstName = firstName
        newUser.lastName = lastName
        newUser.language = language
        newUser.id = uid
        newUser.progress = progressArray2

        database.child("users").child(newUser.id.toString()).child(newUser.userId.toString()).setValue(newUser)
        userId++
    }


    /*
    private fun generateUserId(){
        val uid = Firebase.auth.uid.toString()
        database.child("users").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    userId = dataSnapshot.childrenCount.toString()
                } else {
                    userId = "0"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
     */


    @Override
    private fun reportNewRepair(
        userId: String,
        productId: String,
        partId: String,
        description: String
    ){
        database = Firebase.database.reference

        val uid = Firebase.auth.uid.toString()

        database.child("reports").child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    report.reportId = dataSnapshot.childrenCount.toString()
                } else {
                    report.reportId = "0"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })


        report.productId = productId
        report.partId = partId
        report.description = description

        report.id = uid
        report.userId = userId
        if(report.reportId == null){
            report.reportId = "0"
        }

        database.child("reports").child(report.id!!).child(report.reportId!!).setValue(report);
        Toast.makeText(applicationContext, "${Firebase.database.reference}", Toast.LENGTH_SHORT).show()
    }

/*
    fun initializeDbRef() {
        // [START initialize_database_ref]
        database = Firebase.database.reference
        // [END initialize_database_ref]
    }
 */
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

}