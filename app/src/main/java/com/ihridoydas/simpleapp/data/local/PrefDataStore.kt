package com.ihridoydas.simpleapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Preference data store class
 * @param context get activity context
 */
class PrefDataStore(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appPref")

        //showCase Prompt Completion
        val onShowCaseCompleted = booleanPreferencesKey("onShowCaseCompleted")
    }
    //set and get value

    // set HasCameraPermission into datastore
    suspend fun setOnShowCaseCompleted(onShowCaseCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStore.onShowCaseCompleted] =
                onShowCaseCompleted
        }
    }

    // get HasCameraPermission
    val getOnShowCaseCompleted: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[onShowCaseCompleted] ?: true
        }
}