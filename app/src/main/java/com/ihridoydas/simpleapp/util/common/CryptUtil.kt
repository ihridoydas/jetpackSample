package com.ihridoydas.simpleapp.util.common

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import java.security.KeyStore
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

import javax.crypto.spec.IvParameterSpec

/**
 * 暗号/複合化用サポートクラス
 */
class CryptUtil(private val keyName: String = KeyAliasCrypt) {

    companion object {
        private val tagName = CryptUtil::class.java.simpleName
        private const val AndroidKeyStore = "AndroidKeyStore"
        private const val KeyAliasCrypt = "com.ihridoydas.simpleapp.key.alias.crypt"
        private const val Transformation =
            "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
        private const val Separator = ","
    }

    init {
        initKeyStore()
        loadOrGenerateKey()
    }

    private var keyStore: KeyStore? = null
    private var secretKey: SecretKey? = null

    private fun initKeyStore() {
        Log.d(tagName, "initKeyStore method called.")
        keyStore = KeyStore.getInstance(AndroidKeyStore)?.also {
            it.load(null)
        }
    }

    private fun loadOrGenerateKey() {
        Log.d(tagName, "loadOrGenerateKey method called.")
        val keyStoreEntry = keyStore?.getEntry(keyName, null) ?: run {
            Log.d(tagName, "keyStoreEntry is null.")
            generateKey()
            return
        }
        secretKey = (keyStoreEntry as KeyStore.SecretKeyEntry).secretKey
    }

    private fun generateKey() {
        Log.d(tagName, "generateKey method called.")
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, AndroidKeyStore)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyName,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .build()
        keyGenerator.init(keyGenParameterSpec)
        secretKey = keyGenerator.generateKey()
    }

    /**
     * 平文暗号化用API
     */
    fun encrypt(value: String): String {
        val cipher = Cipher.getInstance(Transformation)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val values = cipher.doFinal(value.toByteArray())
        val iv = Base64.getEncoder().encodeToString(cipher.iv)
        val encrypted = Base64.getEncoder().encodeToString(values)
        return "$encrypted$Separator$iv"
    }

    /**
     * 暗号文複合化用API
     */
    fun decrypt(value: String): String {
        val parts = value.split(Separator)
        if (parts.size != 2) return ""
        val encrypted = Base64.getDecoder().decode(parts[0])
        val iv = Base64.getDecoder().decode(parts[1])
        val cipher = Cipher.getInstance(Transformation)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        return String(cipher.doFinal(encrypted))
    }
}
