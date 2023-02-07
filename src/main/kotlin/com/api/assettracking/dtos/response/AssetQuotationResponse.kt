package com.api.assettracking.dtos.response

import java.math.BigDecimal


data class AssetQuotationResponse(
    val quoteResponse: QuoteListResponse

)

data class QuoteListResponse(
    val result: List<QuoteResponse>

)

data class QuoteResponse(
    val shortName: String?,
    val symbol: String,
    val regularMarketPrice: BigDecimal?
)