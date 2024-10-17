package org.example.securitylabserver.services

import org.apache.tomcat.util.net.openssl.ciphers.Encryption
import org.example.securitylabserver.model.Encryptions
import org.example.securitylabserver.model.User
import org.example.securitylabserver.repositories.EncryptionRepository

class EncryptionService(
    private val encryptionRepository: EncryptionRepository,
    private val passwordHasher: PasswordHasher) {


    fun createRawEncryption(user : User, raw : String) {
        val enc =  Encryptions(
            encryptedRaw = raw,
            user = user,
            grasshopper = null.toString(),
            rsa = null.toString(),
        )
        encryptionRepository.save(enc)
    }


    fun encryptGrasshopper(id : Long ,data: String) {
        val grassHopper = passwordHasher.encryptGrasshopper(data)
        val encOptional = encryptionRepository.findById(id)

        try {
            if (encOptional.isPresent) {
                val enc = encOptional.get()
                enc.grasshopper = grassHopper
                encryptionRepository.save(enc)
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun decryptGrasshopper(id: Long): String {
        val encOptional = encryptionRepository.findById(id)
        if (encOptional.isPresent) {
            val enc = encOptional.get()
            val grasshopper = enc.grasshopper
            return passwordHasher.decryptGrasshopper(grasshopper)
        }
        return ""
    }

}