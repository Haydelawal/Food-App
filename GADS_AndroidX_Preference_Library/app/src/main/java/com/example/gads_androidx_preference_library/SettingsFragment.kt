package com.example.gads_androidx_preference_library

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val dataStore = MyDataStore()

        // enable preference datastore for entire hierarchy and disable the shared preferences
        //  preferenceManager.preferenceDataStore = dataStore


        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        //        // navigating to a new activity
        //        val myIntent = Intent(context, SecondActivity::class.java)
        //        accSettingsPref?.intent = myIntent

        //        accSettingsPref?.setOnPreferenceClickListener {
//            Toast.makeText(context, "MDFFF", Toast.LENGTH_SHORT).show()
//
//
//
//            true
//        }
        accSettingsPref?.setOnPreferenceClickListener {


            val action =
                SettingsFragmentDirections.actionSettingsFragmentToAccountSettingsFragment()
            findNavController().navigate(action)
            true
        }

        // read preferences in fragment

        //step1
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // step2
        val autoReplyTimeValue =
            sharedPreference?.getString(getString(R.string.key_auto_reply_time), "")

        Log.d("FRAGMENT", "Auto Reply Time: $autoReplyTimeValue")


        val autoDownloadValue =
            sharedPreference?.getBoolean(R.string.key_auto_download.toString(), false)

        // Preference.OnPreferenceChangeListener
        val statusPref = findPreference<EditTextPreference>("key_status")
        statusPref?.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener { preference, newValue ->

            // true: accept the new value.
            // false: reject the new value
            val newStatus = newValue as String
            if (newStatus.contains("bad")) {
                Toast.makeText(context, "Cant contain bad words", Toast.LENGTH_SHORT).show()
                false
            } else {
                true
            }
        })

        // updating SwitchPreferenceCompat dynamically
        val notificationPref =
            findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        notificationPref?.summaryProvider =
            Preference.SummaryProvider<SwitchPreferenceCompat> { switchPref ->

                if (switchPref.isChecked) {
                    "Status: ON"
                } else {
                    "Status: OFF"
                }
            }

        // using pref data store on the notificationPref
        notificationPref?.preferenceDataStore = dataStore

        // opening webpage with intent
        val privacyPolicyPref = findPreference<Preference>("key_privacy_policy")
        val myIntent = Intent(Intent.ACTION_VIEW)
        myIntent.data = Uri.parse("https://www.google.com")
        privacyPolicyPref?.intent = myIntent


    }
}

// pref datastore
class MyDataStore : PreferenceDataStore() {

    // override methods as per your needs
    // DO NOT OVERRIDE METHODS YOU DONT WANT TO USE
    // AFTER OVERRIDING, REMOVE THE SUPERCALL COZ IT CAN THROW (UnsupportedOperationException)


    // since the notificationPref is a boolean type, override the boolean get and put functions

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {

        if (key == "key_new_msg_notif") {
            // Retrieve value from cloud or local db
            Log.i("DataStore", "getBoolean executed for $key")
        }

        return defValue
    }

    override fun putBoolean(key: String?, value: Boolean) {

        if (key == "key_new_msg_notif") {
            // Save value to cloud or local db
            Log.i("DataStore", "putBoolean executed for $key with new value: $value")
        }
    }
}