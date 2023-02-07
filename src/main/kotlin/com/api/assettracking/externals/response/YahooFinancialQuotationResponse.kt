package com.api.assettracking.externals.response

import java.math.BigDecimal


data class YahooFinancialQuotationResponse(
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