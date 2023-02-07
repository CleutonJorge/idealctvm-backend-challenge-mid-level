package com.api.assettracking.services.persistence

import com.api.assettracking.models.AssetModel
import com.api.assettracking.repositories.AssetRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class AssetPersistenceService(
    val assetRepository: AssetRepository
) {

    fun saveAsset(symbol: String, displayName: String?, regularMarketPrice: BigDecimal?): AssetModel {
        val asset = assetRepository.findBySymbol(symbol)
        return verifyingAssetCreateOrUpdate(asset, symbol, displayName, regularMarketPrice)
    }

    fun verifyingAssetCreateOrUpdate(
        asset: Optional<AssetModel>,
        symbol: String,
        displayName: String?,
        regularMarketPrice: BigDecimal?
    ): AssetModel {
        when (asset.isEmpty) {
            true -> return assetRepository.save(
                AssetModel(
                    displayName = displayName ?: ("Ativo $symbol"),
                    symbol = symbol,
                    regularMarketPrice = regularMarketPrice ?: BigDecimal.ZERO,
                )
            )

            false ->
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