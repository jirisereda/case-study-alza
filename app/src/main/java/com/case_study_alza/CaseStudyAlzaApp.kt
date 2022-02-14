package com.case_study_alza

import android.app.Application
import com.case_study_alza.di.AlzaApiEndpointUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import timber.log.Timber

@HiltAndroidApp
class CaseStudyAlzaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @AlzaApiEndpointUrl
    fun provideApiEndpointUrl(): String =
        BuildConfig.BASE_END_POINT_URL

}