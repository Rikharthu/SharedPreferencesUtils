package com.example.sharedpreferencesutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.sharedpreferencesutils.java.PreferenceHelper
import com.example.sharedpreferencesutils.kotlin.PreferenceHelper.defaultPrefs
import com.example.sharedpreferencesutils.kotlin.PreferenceHelper.get
import com.example.sharedpreferencesutils.kotlin.PreferenceHelper.set

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Java:
        PreferenceHelper.setValue(this, "greeting_java", "Hello from Java!")
        val javaGreeting: String = PreferenceHelper.getValue(this, "greeting_java", String::class.java, null) as String

        // Kotlin:
        val prefs = defaultPrefs(this)
        prefs["greeting_kotlin"] = "Hello from Kotlin!"
        val kotlinGreeting: String? = prefs["greeting_kotlin"]
        val kotlinGreeting2: String? = prefs["greeting_kotlinX", "Default greeting"]

        Log.d(TAG, "Java greeting: $javaGreeting")
        Log.d(TAG, "Kotlin greeting1: $kotlinGreeting")
        Log.d(TAG, "Kotlin greeting2: $kotlinGreeting2")
    }
}
