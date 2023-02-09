package com.api.assettracking.dtos.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class AccompanimentResponse(

    val name: String,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime?,
    val assetList: List<AssetResponse>,

    )