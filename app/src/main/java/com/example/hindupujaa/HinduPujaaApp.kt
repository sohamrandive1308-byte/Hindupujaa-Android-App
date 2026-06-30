package com.example.hindupujaa

import android.app.Application
import android.util.Log
import com.example.hindupujaa.core.data.util.FirebaseSeeder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HinduPujaaApp : Application() {

    @Inject
    lateinit var firebaseSeeder: FirebaseSeeder

    override fun onCreate() {
        super.onCreate()
        try {
            // We don't need manual initializeApp with the google-services plugin
            // Seeding on every startup can be slow/problematic, 
            // usually better to do this once or in a worker.
            // firebaseSeeder.seedDatabase()
        } catch (e: Exception) {
            Log.e("HinduPujaaApp", "Error during Application onCreate", e)
        }
    }
}
