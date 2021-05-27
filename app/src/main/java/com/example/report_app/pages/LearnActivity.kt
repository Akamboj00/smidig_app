package com.example.report_app.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.report_app.R
import com.example.report_app.database.Database
import com.example.report_app.database.ProgressTemplate
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.navigation_view.view.*

class LearnActivity : AppCompatActivity() {

    private lateinit var db: Database
    private var progressStatus: Int = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        db = Database(this)

        runOnUiThread {
            setNavigation()
            val newProgress = db.getProgress
            newProgress.progress1 = 50f

            db.updateProgress(newProgress)
            db.setProgress()
            setProgress()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setNavigation(){
        // Set Page Button Active
        navigation.learnBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.bright_blue))
        navigation.learnBtn.navigationLearnIcon.backgroundTintList = ColorStateList.valueOf(resources.getColor(
            R.color.white
        ))
        navigation.learnBtn.learnText.setTextColor(resources.getColor(R.color.white))

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

    @SuppressLint("SetTextI18n")
    private fun setProgress(){
        val progressData: ProgressTemplate = db.getProgress
        val calcProgress: Int = progressData.progress1!!.toInt() + progressData.progress2!!.toInt() + progressData.progress3!!.toInt() + progressData.progress4!!.toInt() + progressData.progress5!!.toInt() + progressData.progress6!!.toInt()
        progressStatus = calcProgress / 6

        progressBar.progress = progressStatus;
        learnProgressPercentText.text = "${progressStatus}%"

        progressBar1.progress = progressData.progress1!!.toInt()
        progressBar2.progress = progressData.progress2!!.toInt()
        progressBar3.progress = progressData.progress3!!.toInt()
        progressBar4.progress = progressData.progress4!!.toInt()
        progressBar5.progress = progressData.progress5!!.toInt()
        progressBar6.progress = progressData.progress6!!.toInt()

        learnSunBellPercent.text = "${progressData.progress1!!.toInt()}%"
        learnMoveSmartPercent.text = "${progressData.progress2!!.toInt()}%"
        learnStartPercentage.text = "${progressData.progress3!!.toInt()}%"
        learnSunTurtlePercent.text = "${progressData.progress4!!.toInt()}%"
        learnHomePercent.text = "${progressData.progress5!!.toInt()}%"
        learnReportPercentage.text = "${progressData.progress6!!.toInt()}%"
    }
}