package org.example.securitylabserver.services

import org.bouncycastle.jcajce.provider.digest.GOST3411
import org.bouncycastle.util.encoders.Hex
import java.security.SecureRandom

class PasswordHasher {

    fun generateSalt(): ByteArray {
        val salt = ByteArray(32)
        SecureRandom().nextBytes(salt)
        return salt
    }

    fun hashPassword(password: String, salt: ByteArray): String {
        val digest = GOST3411.Digest2012_512()
        val passwordBytes = password.toByteArray()
        digest.update(salt)
        digest.update(passwordBytes)
        val hash = digest.digest()
        return Hex.toHexString(hash)
    }

    fun verifyPassword(password: String, salt: ByteArray, storedHash: String): Boolean {
        val hash = hashPassword(password, salt)
        return hash == storedHash
    }
}