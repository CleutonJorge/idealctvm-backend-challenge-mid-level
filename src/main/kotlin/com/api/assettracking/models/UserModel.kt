package com.api.assettracking.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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

    @Column(nullable = false, length = 10)
    val type: String? = null,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private var accompaniment: List<AccompanimentModel> = mutableListOf()
)