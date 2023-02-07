package com.api.assettracking.dtos.response

import java.math.BigDecimal

data class QuoteResponse(
    val name: String,
    val symbol: String,
    val price: BigDecimal
)