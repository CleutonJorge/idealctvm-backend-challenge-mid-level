package com.api.assettracking.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "TB_USER")
class UserModel (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false, length = 70)
    val fullName: String,

    @Column(nullable = false, unique = true, length = 14)
    val documentNumber: Long,

    @Column(nullable = false, unique = true, length = 10)
    private val type: String? = null,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "user")
    var accompaniment: List<AccompanimentModel> = mutableListOf()
)