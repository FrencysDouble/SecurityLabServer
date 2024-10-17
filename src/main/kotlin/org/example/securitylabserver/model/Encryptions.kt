package org.example.securitylabserver.model

import jakarta.persistence.*

@Entity
@Table(name = "encryptions")
data class Encryptions (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val encryptedRaw: String,

    @Column(nullable = true)
    var grasshopper : String,

    @Column(nullable = true)
    val rsa : String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)