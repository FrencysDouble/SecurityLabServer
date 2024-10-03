package org.example.securitylabserver.controllers

import org.example.securitylabserver.model.User
import org.example.securitylabserver.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser(
        @RequestParam username : String,
        @RequestParam password : String
    ) : ResponseEntity<String>
    {
        return try {
            userService.createUser(username,password,"none")
            ResponseEntity.ok("Created $username")
        }
        catch (e:Exception)
        {
            ResponseEntity.badRequest().body("${e.message}")
        }
    }

    @PostMapping("/verify")
    fun verifyUserPassword(
        @RequestParam username: String,
        @RequestParam password: String
    ): ResponseEntity<String> {
        val isValid = userService.verifyUserPassword(username, password)
        return if (isValid) {
            ResponseEntity.ok("Пароль для пользователя $username корректен.")
        } else {
            ResponseEntity.badRequest().body("Неверный пароль для пользователя $username.")
        }
    }


    @GetMapping("/get")
    fun getAllUsers() : MutableList<User>
    {
        return userService.getAllUsers()
    }

    @GetMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            userService.deleteUser(id)
            ResponseEntity.ok("User with ID $id deleted successfully.")
        } catch (e: NullPointerException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

}