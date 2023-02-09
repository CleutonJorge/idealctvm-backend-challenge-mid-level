package com.api.assettracking.services

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.services.persistence.AssetPersistenceService
import org.springframework.stereotype.Service

@Service
class AssetService(
    val assetQuotationService: AssetQuotationService,
    val assetPersistenceService: AssetPersistenceService,
    val accompanimentService: AccompanimentService,
) {
    fun addAssetAccompaniment(documentNumber: Long, assetSymbol: String): AssetResponse {
        val assetQuotation = assetQuotationService.getAssetQuotation(assetSymbol)
        val asset = assetPersistenceService.saveAsset(
            assetQuotation.symbol,
            assetQuotation.name,
            assetQuotation.price
        )
        accompanimentService.updateAccompaniment(documentNumber, assetSymbol)
        return asset.toResponse()
    }

    private fun AssetModel.toResponse(): AssetResponse {
        return AssetResponse(
            displayName = displayName,
            regularMarketPrice = regularMarketPrice,
            symbol = symbol
        )
    }

}