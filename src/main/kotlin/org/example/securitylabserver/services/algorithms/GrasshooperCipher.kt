package org.example.securitylabserver.services.algorithms

import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.crypto.KeyGenerator

class GrasshooperCipher() {

    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKey: SecretKey = SecretKeySpec(generateKey(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun decrypt(encryptedData: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKey: SecretKey = SecretKeySpec(generateKey(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        return String(decryptedBytes)
    }

    companion object {
        fun generateKey(): ByteArray {
            val keyGen = KeyGenerator.getInstance("AES")
            keyGen.init(128) // Размер ключа 128 бит
            return keyGen.generateKey().encoded
        }
    }
}