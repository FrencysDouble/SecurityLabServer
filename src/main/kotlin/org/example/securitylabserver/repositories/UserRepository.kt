package org.example.securitylabserver.repositories

import org.example.securitylabserver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User,Long> {
    fun findByUsername(userName : String) : User?
}