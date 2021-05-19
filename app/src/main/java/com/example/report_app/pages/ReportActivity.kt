package com.example.report_app.pages

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.report_app.R
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.navigation_view.view.*

class ReportActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        runOnUiThread {
            setNavigation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setNavigation(){
        // Set Page Button Active
        navigation.reportBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.bright_blue))
        navigation.reportBtn.navigationReportIcon.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(
                R.color.white
            )
        )
        navigation.reportBtn.reportText.setTextColor(resources.getColor(R.color.white))

        navigation.reportBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, ReportActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.learnBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, LearnActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.profileBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }
        navigation.languageBtn.setOnClickListener{
            this.startActivity(Intent(applicationContext, LanguageActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }
}