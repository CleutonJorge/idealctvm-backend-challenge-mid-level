package com.api.assettracking.services.persistence

import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.AssetQuotationResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.AssetRepository
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal
import java.util.*

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