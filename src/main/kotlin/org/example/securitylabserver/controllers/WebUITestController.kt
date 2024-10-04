package org.example.securitylabserver.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/api/v1/ui")
class WebUITestController {
    @GetMapping("/main")
    fun showUsersPage(): String {
        return "usersUI" // Это имя HTML-шаблона, который будет отображаться
    }
}