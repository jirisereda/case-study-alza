package com.case_study_alza

import android.app.Application
import timber.log.Timber

class CaseStudyAlzaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}