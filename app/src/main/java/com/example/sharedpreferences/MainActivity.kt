package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferences.databinding.ActivityMainBinding

const val PREFERENCES = "Preferences"
const val PREF_KEY = "Pref_Key"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        binding.etTextForSave.setText(preferences.getString(PREF_KEY, ""))

        binding.btnShared.setOnClickListener {
            val value = binding.etTextForSave.text.toString()
            preferences.edit()
                .putString(PREF_KEY, value)
                .apply()
        }
    }
}