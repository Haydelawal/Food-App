package com.example.gads_androidx_preference_library

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class DummySettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}