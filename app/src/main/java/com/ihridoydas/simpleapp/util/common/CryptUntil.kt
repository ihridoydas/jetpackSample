package com.ihridoydas.simpleapp.util.common

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptUntil {

    private val ALGORITHM = "AES"
    private val MODE = "AES"
    private val IV = "abcdefgh"

    val key = SecretKeySpec("0123456789ABCDEF".toByteArray(), "AES")

    /**
     * 引数の文字列を暗号化する(Base64対応)
     *
     * @param value     暗号化対象文字列
     * @param secretKey 暗号化キー
     * @return String 暗号化済み文字列
     */
    @Throws(NoSuchPaddingException::class,
            NoSuchAlgorithmException::class,
            InvalidAlgorithmParameterException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class)
    fun encrypt(value: String, secretKey: String): String? {
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(IV.toByteArray()))
        val values = cipher.doFinal(value.toByteArray())
        return Base64.getEncoder().encodeToString(values)
    }

    /**
     * 引数のBase64された文字列を復号化する
     *
     * @param value     復号化対象文字列
     * @param secretKey 復号化キー
     * @return String 復号化済み文字列
     */
    @Throws(NoSuchPaddingException::class,
            NoSuchAlgorithmException::class,
            InvalidAlgorithmParameterException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class)
    fun decrypt(value: String?, secretKey: String): String? {
        val values: ByteArray = Base64.getDecoder().decode(value)
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(IV.toByteArray()))
        return String(cipher.doFinal(values))
    }

    /**
     * ファイル暗号化
     * @param inputFilePath ファイルパス
     * @param outputFilePath 暗号化されたファイルの置き場所
     */
    fun fileEncrypt(inputFilePath: String, outputFilePath: String)  {

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        cipher.init(Cipher.ENCRYPT_MODE, key)

        val fis = FileInputStream(inputFilePath)
        val fos = FileOutputStream(outputFilePath)
        val cos = CipherOutputStream(fos, cipher)
        fos.write(cipher.getIV())
        val a = ByteArray(8)
        var i = fis.read(a)
        while (i != -1) {
            cos.write(a, 0, i)
            i = fis.read(a)
        }
        cos.flush()
        cos.close()

    }

    /**
     * ファイル復号化
     * @param inputFilePath 暗号化されたファイル
     * @param outputFilePath 復号化したファイルの置き場所
     */
    fun fileDecrypt(inputFilePath: String,outputFilePath: String) {

        val fis = FileInputStream(inputFilePath)
        val iv = ByteArray(16)
        fis.read(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivspec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, key, ivspec)
        val cis = CipherInputStream(fis, cipher)
        val reader = cis.bufferedReader()

        val file = File(outputFilePath)
        val fw = FileWriter(file)
        var line: String? = reader.readLine()
        while (line != null) {
            fw.write(line)
            line = reader.readLine()
        }
        reader.close()
        fw.close()
    }
}
