package com.ihridoydas.simpleapp.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ihridoydas.simpleapp.util.common.CryptUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Preference data store class
 * @param context get activity context
 */
class PrefDataStore(private val context: Context) {

    private val defaultLanguage = 1

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appPref")

        //showCase Prompt Completion
        val onShowCaseCompleted = booleanPreferencesKey("onShowCaseCompleted")
        //Skip Button Click
        val onShowSkipCompleted = booleanPreferencesKey("onSkipCaseCompleted")

        // StartScreen View or not
        val isStartScreenCover = booleanPreferencesKey("isStartScreenCover")

        //For MultiLanguage
        val PREF_LANGUAGE = intPreferencesKey("language")


        // データベースパスワード
        val databasePass = stringPreferencesKey("databasePass")

        // デバイスID
        val deviceId = stringPreferencesKey("deviceId")
    }
    //set and get value

    // set Show Case in Start Screen
    suspend fun setOnShowCaseCompleted(onShowCaseCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStore.onShowCaseCompleted] =
                onShowCaseCompleted
        }
    }

    // get Show Case in Start Screen
    val getOnShowCaseCompleted: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[onShowCaseCompleted] ?: true
        }

    // set Skip Case in Start Screen
    suspend fun setOnSkipCaseCompleted(onShowCaseCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStore.onShowSkipCompleted] =
                onShowCaseCompleted
        }
    }

    // get Skip Case in Start Screen
    val getOnSkipCaseCompleted: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[onShowSkipCompleted] ?: true
        }



    // set isStartScreenCover into datastore
    suspend fun setIsStartScreenCover(isStartScreenCover: Boolean) {
        context.dataStore.edit { preferences ->

            preferences[PrefDataStore.isStartScreenCover] = isStartScreenCover
        }
    }

    // get isStartScreenCover
    val getIsStartScreenCover: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->

            preferences[isStartScreenCover] ?: false
        }


    //For MultiLanguage
    suspend fun setLanguage(language: Int) {
        context.dataStore.edit { preferences ->
            preferences[PREF_LANGUAGE] = language
        }
    }

    val getLanguage: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_LANGUAGE] ?: defaultLanguage
        }


    suspend fun setDatabasePass(encryptPass: String) {
        context.dataStore.edit { preferences ->
            preferences[databasePass] = encryptPass
        }
    }

    val getDatabasePass: Flow<String?> = context.dataStore.data
        .map { preferences ->
            return@map preferences[databasePass]
        }


    // set deviceId into datastore
    suspend fun setDeviceId(deviceId: String) {
        context.dataStore.edit { preferences ->

            val cryptDeviceId: String?

            try {
                val cryptUntil = CryptUtil()
                cryptDeviceId = cryptUntil.encrypt(deviceId)
                if (cryptDeviceId != null) {
                    Log.v("CCC crypt deviceId", cryptDeviceId)
                    preferences[PrefDataStore.deviceId] = cryptDeviceId
                }
            } catch (e: Exception) {
                Log.e("CCC exception", e.toString())
            }
        }
    }


    // get deviceId
    val getDeviceId: Flow<String?> = context.dataStore.data
        .map { preferences ->

            val cryptDeviceId: String? = preferences[deviceId]
            if (cryptDeviceId != null) {
                try {
                    val cryptUntil = CryptUtil()
                    cryptUntil.decrypt(cryptDeviceId)?.also {
                        Log.v("CCC encrypt deviceId", it.uppercase())
                        return@map it.uppercase()
                    } ?: run {
                        return@map null
                    }
                } catch (e: Exception) {
                    Log.e("CCC exception", e.toString())
                    return@map null
                }
            } else {
                return@map null
            }
        }
}