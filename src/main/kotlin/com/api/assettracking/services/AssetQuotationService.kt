package com.api.assettracking.services

import com.api.assettracking.dtos.response.QuoteResponse
import com.api.assettracking.exceptions.AsserQuotationNotExistException
import com.api.assettracking.interfaces.AssetQuotationInterface
import org.springframework.stereotype.Service

@Service
class AssetQuotationService(
    val assetQuotation: AssetQuotationInterface,
) {

    fun getAssetQuotation(asset: String): QuoteResponse {
        return assetQuotation.getExternalQuotation(asset)
            ?: throw AsserQuotationNotExistException("the asset does not exists: $asset")
    }
}