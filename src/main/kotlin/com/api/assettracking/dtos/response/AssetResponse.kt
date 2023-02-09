package com.api.assettracking.dtos.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.*

class AssetResponse(

    val displayName: String,
    val symbol: String,
    val regularMarketPrice: BigDecimal,

    )