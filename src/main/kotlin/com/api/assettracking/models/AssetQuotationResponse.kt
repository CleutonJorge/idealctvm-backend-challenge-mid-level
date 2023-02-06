package com.api.assettracking.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*


data class AssetQuotationResponse(
    val quoteResponse: QuoteListResponse

)

data class QuoteListResponse(
    val result: List<QuoteResponse>

)

data class QuoteResponse(
    val quoteSourceName: String,
    val symbol: String,
    val regularMarketPrice: BigDecimal
)