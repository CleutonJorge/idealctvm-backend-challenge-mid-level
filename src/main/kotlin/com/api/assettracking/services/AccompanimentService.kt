package com.api.assettracking.services

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.dtos.response.UserResponse
import com.api.assettracking.enums.AssetAccompanimentOrderType
import com.api.assettracking.enums.DocumentType
import com.api.assettracking.models.*
import com.api.assettracking.services.persistence.AccompanimentPersistenceService
import com.api.assettracking.services.persistence.AssetPersistenceService
import com.api.assettracking.services.persistence.UserPersistenceService
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class AccompanimentService(
    val accompanimentPersistenceService: AccompanimentPersistenceService,
) {

    fun getAccompaniment(documentNumber: Long, assetOrder: AssetAccompanimentOrderType): AccompanimentResponse {
        val compareOrder = getCompareOrder(assetOrder)
        return accompanimentPersistenceService.getAccompaniment(documentNumber, compareOrder).toResponse()
    }

    fun addAccompaniment(documentNumber: Long): AccompanimentResponse {
        return accompanimentPersistenceService.saveAccompaniment(documentNumber).toResponse()
    }

    fun updateAccompaniment(documentNumber: Long, assetSymbol: String): AccompanimentResponse {
        return accompanimentPersistenceService.updateAccompaniment(documentNumber, assetSymbol).toResponse()
    }

    private fun getCompareOrder(assetOrder: AssetAccompanimentOrderType): Comparator<AssetModel> {
        return when (assetOrder) {
            AssetAccompanimentOrderType.ASSET_SYMBOL -> compareBy(AssetModel::symbol)
            AssetAccompanimentOrderType.ASSET_NAME -> compareBy(AssetModel::displayName)
            AssetAccompanimentOrderType.ASSET_PRICE -> compareBy(AssetModel::regularMarketPrice)
        }
    }

    private fun AccompanimentModel.toResponse() : AccompanimentResponse {
        return AccompanimentResponse(
            name = name,
            createAt = createAt,
            updateAt = updateAt,
            assetList = assets.map { AssetResponse(
                displayName = it.displayName,
                regularMarketPrice = it.regularMarketPrice,
                symbol = it.symbol
            ) }
        )
    }
}