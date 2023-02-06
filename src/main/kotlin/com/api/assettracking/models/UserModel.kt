package com.api.assettracking.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "TB_USER")
class UserModel (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID,

    @Column(nullable = false, length = 70)
    private val fullName: String,

    @Column(nullable = false, unique = true, length = 14)
    private val documentNumber: Long,

    @Column(nullable = false, unique = true, length = 10)
    private val type: String? = null,

    @OneToMany
    @JoinColumn(name = "accompaniment_id", nullable = false)
    var accompaniment: Set<AccompanimentModel> = setOf()
)