package com.api.assettracking.services.persistence

import com.api.assettracking.models.AssetModel
import com.api.assettracking.repositories.AssetRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AssetPersistenceService(
    val assetRepository: AssetRepository
) {

    fun saveAsset(symbol: String, displayName: String?, regularMarketPrice: BigDecimal?): AssetModel {
        val asset = assetRepository.findBySymbol(symbol)
        if (asset.isEmpty) {
            return assetRepository.save(
                AssetModel(
                    displayName = displayName ?: ("Ativo $symbol"),
                    symbol = symbol,
                    regularMarketPrice = regularMarketPrice ?: BigDecimal.ZERO,
                )
            )
        } else {
            return assetRepository.save(
                AssetModel(
                    displayName = displayName ?: ("Ativo $symbol"),
                    symbol = symbol,
                    regularMarketPrice = regularMarketPrice ?: BigDecimal.ZERO,
                    id = asset.get().id
                )
            )
        }
    }
}