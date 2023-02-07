package com.api.assettracking.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.*

class AssetDTO(

    @Size(max = 14)
    val documentNumber: Long,
    @Size(max = 10)
    val symbol: String,

    )