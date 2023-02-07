package com.api.assettracking.interfaces

import com.api.assettracking.dtos.response.QuoteResponse

interface AssetQuotationInterface {
    fun getExternalQuotation(asset: String) : QuoteResponse?
}