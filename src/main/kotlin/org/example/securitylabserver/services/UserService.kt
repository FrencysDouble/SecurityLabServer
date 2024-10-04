package org.example.securitylabserver.services

import org.bouncycastle.util.encoders.Hex
import org.example.securitylabserver.model.RoleEnum
import org.example.securitylabserver.model.User
import org.example.securitylabserver.repositories.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    private val passwordHasher = PasswordHasher()

    //Post requests

    fun createUser(username: String, password: String, role: String): User {
        val salt = passwordHasher.generateSalt()
        val hashedPassword = passwordHasher.hashPassword(password, salt)
        val user = User(
            username = username,
            password = hashedPassword,
            salt = Hex.toHexString(salt),
            role = role
        )
        return userRepository.save(user)
    }

    fun verifyUserPassword(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username) ?: return false
        val salt = Hex.decode(user.salt)
        return passwordHasher.verifyPassword(password, salt, user.password)
    }

    //GET Requests

    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }

    fun deleteUser(id: Long) {
        val user = userRepository.findById(id).orElse(null)
        if (user != null) {
            userRepository.delete(user)
        } else {
            throw ChangeSetPersister.NotFoundException()
        }
    }

    fun changeUserRole(id: Long, role: RoleEnum) {
        val user = userRepository.findById(id).orElse(null)
        if (user != null) {
            user.role = role.name
            userRepository.save(user)
        } else {
            throw ChangeSetPersister.NotFoundException()
        }
    }
}