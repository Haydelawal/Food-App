package com.example.gads_androidx_preference_library

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.example.gads_androidx_preference_library.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //step1
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        // step2
        val autoReplyTimeValue =
            sharedPreference?.getString(getString(R.string.key_auto_reply_time), "")
//
//        Log.d("ACTIVITY ", "Auto Reply Time: $autoReplyTimeValue")
//
//        Toast.makeText(this, " Value is: $autoReplyTimeValue", Toast.LENGTH_SHORT).show()

        // reading value of multi select list preference
        // step 1
        // val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        // step2
        val publicInfo: Set<String>? = sharedPreference.getStringSet(getString(R.string.key_public_info), null)

        Toast.makeText(this, "$publicInfo", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navHost)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // called only after the preference value has changed
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        // dont forget to register it in on resume
        // dont forget to unregister it in on pause coz of memory leaks

        if (key == getString(R.string.key_status)) {
            val newStatus = sharedPreferences?.getString(key, "")
            Toast.makeText(this, "New Status: $newStatus", Toast.LENGTH_SHORT).show()
        }

        if (key == getString(R.string.key_auto_reply)) {

            val autoreply = sharedPreferences?.getBoolean(key, false)
            if (autoreply!!) {
                Toast.makeText(this, "Auto Reply ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Auto Reply OFF", Toast.LENGTH_SHORT).show()

            }

        }
    }

    override fun onResume() {
        // dont forget to register it in on resume

        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        // dont forget to unregister it in on pause coz of memory leaks

        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

}