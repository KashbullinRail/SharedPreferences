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

    private val preferencesListener =
        SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
            if (key == PREF_KEY) {
                binding.tvSharedPreferPrev.text = preferences.getString(key, "")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val currentValue = preferences.getString(PREF_KEY, "")

        binding.etTextForSave.setText(currentValue)
        binding.tvSharedPreferPrev.text = currentValue

        binding.btnShared.setOnClickListener {
            val value = binding.etTextForSave.text.toString()
            preferences.edit()
                .putString(PREF_KEY, value)
                .apply()
        }
        preferences.registerOnSharedPreferenceChangeListener(preferencesListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
    }

}