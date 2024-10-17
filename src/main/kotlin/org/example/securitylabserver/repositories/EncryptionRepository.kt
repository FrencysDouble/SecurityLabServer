package org.example.securitylabserver.repositories

import org.apache.tomcat.util.net.openssl.ciphers.Encryption
import org.example.securitylabserver.model.Encryptions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EncryptionRepository : JpaRepository<Encryptions, Long> {



}