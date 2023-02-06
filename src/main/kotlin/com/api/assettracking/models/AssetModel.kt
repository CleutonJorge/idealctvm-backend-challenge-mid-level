package com.api.assettracking.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "TB_ASSET")
class AssetModel (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false, length = 70)
    private val displayName: String? = null,

    @Column(nullable = false, unique = true, length = 10)
    private val symbol: String? = null,

    @Column(nullable = false)
    private val regularMarketPrice: BigDecimal? = null,

    @ManyToMany(mappedBy = "assets")
    @JsonIgnoreProperties("assets")
    var accompaniments: List<AccompanimentModel> = mutableListOf()
)