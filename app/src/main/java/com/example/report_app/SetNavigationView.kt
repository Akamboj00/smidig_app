package com.example.report_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.report_app.components.NavigationView
import com.example.report_app.pages.LanguageActivity
import com.example.report_app.pages.LearnActivity
import com.example.report_app.pages.ProfileActivity
import com.example.report_app.pages.ReportActivity
import kotlinx.android.synthetic.main.navigation_view.view.*

/*
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SetNavigationView(navigationView: NavigationView, context: Context) {

    init {
        setNavigation(navigationView, context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public fun setNavigation(navigationView: NavigationView, context: Context){
        // Set Page Button Active

        navigationView.reportBtn.backgroundTintList = ColorStateList.valueOf(ResourcesHelper.brightBlue)
        navigationView.reportBtn.navigationReportIcon.backgroundTintList = ColorStateList.valueOf(ResourcesHelper.white)
        navigationView.reportBtn.reportText.setTextColor(ColorStateList.valueOf(ResourcesHelper.white))

        navigationView.reportBtn.setOnClickListener{
            context.startActivity(Intent(context, ReportActivity::class.java))
            (context as Activity).overridePendingTransition(0, 0)
        }
        navigationView.learnBtn.setOnClickListener{
            context.startActivity(Intent(context, LearnActivity::class.java))
            (context as Activity).overridePendingTransition(0, 0)
        }
        navigationView.profileBtn.setOnClickListener{
            context.startActivity(Intent(context, ProfileActivity::class.java))
            (context as Activity).overridePendingTransition(0, 0)
        }
        navigationView.languageBtn.setOnClickListener{
            context.startActivity(Intent(context, LanguageActivity::class.java))
            (context as Activity).overridePendingTransition(0, 0)
        }
    }


}

class ResourcesHelper {
    companion object {
        val brightBlue = Resources.getSystem().getColor(R.color.bright_blue)
        val white = Resources.getSystem().getColor(R.color.white)
    }
}
 */
