
package com.ihridoydas.simpleapp.util.extensions

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.*

object DatabaseEncryptionUtil {

    private val TAG = DatabaseEncryptionUtil::class.java.simpleName
    private const val KEY_STORE_NAME = "AndroidKeyStore"
    const val KEY_ALIAS = "jp.cognivision.cpbmobile.key.alias"
    var keyStore: KeyStore? = null
    var encryptedPass: String = ""
    private lateinit var prefDataStore: PrefDataStore

    fun execute(prefDataStore: PrefDataStore) {
        Log.d(TAG, "execute called.")
        DatabaseEncryptionUtil.prefDataStore = prefDataStore
        runBlocking {
            settingDeviceId()
            prepareKeyStore()
        }
    }

    /**
     * デバイスID設定処理
     */
    private suspend fun settingDeviceId() = withContext(Dispatchers.IO) {
        Log.d(TAG, "settingDeviceId Called.")
        prefDataStore.getDeviceId.first() ?: run {
            val deviceId = UUID.randomUUID().toString()
            prefDataStore.setDeviceId(deviceId)
        }
    }

    /**
     * KeyStoreの準備
     */
    private fun prepareKeyStore() {
        if (encryptedPass.isEmpty()) {
            encryptedPass = runBlocking {
                prefDataStore.getDatabasePass.first() ?: ""
            }
        }

        val partDeviceId = runBlocking {
            prefDataStore.getDeviceId.first()?.substring(0, 8) ?: ""
        }
        if (partDeviceId.isEmpty()) {
            Log.w(TAG, "deviceId is not found.")
        } else {
            getKeyStore(partDeviceId)
        }
        return
    }

    /**
     * KeyStoreの取得
     * @param partDeviceId デバイスIDの一部
     */
    private fun getKeyStore(partDeviceId: String) {
        try {
            keyStore = KeyStore.getInstance(KEY_STORE_NAME)?.also {
                it.load(null)
                createNewKey(keyStore = it, partDeviceId = partDeviceId)
            }
        } catch (e: Exception) {
            Log.e(TAG, "error: Failed to get keyStore instance. $e")
        }
    }

    /**
     * KeyStore用のキーを新規作成
     * @param keyStore キーストア
     * @param alias エイリアス
     * @param partDeviceId デバイスIDの一部
     */
    private fun createNewKey(keyStore: KeyStore, alias: String = KEY_ALIAS, partDeviceId: String) {
        try {
            if (!keyStore.containsAlias(alias)) {
                val keyPairGenerator =
                    KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEY_STORE_NAME)
                keyPairGenerator.initialize(
                    KeyGenParameterSpec.Builder(
                        alias,
                        KeyProperties.PURPOSE_DECRYPT
                    )
                        .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                        .build()
                )
                keyPairGenerator.generateKeyPair()
                partDeviceId.trim().encryptString(keyStore).also {
                    encryptedPass = it
                    CoroutineScope(Dispatchers.IO).launch {
                        prefDataStore.setDatabasePass(it)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "error: Failed to generate key for keyStore. $e")
        }
    }
}
