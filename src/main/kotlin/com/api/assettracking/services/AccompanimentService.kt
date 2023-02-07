package com.api.assettracking.services

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

    fun getAccompaniment(documentNumber: Long, assetOrder: AssetAccompanimentOrderType) : AccompanimentModel {
        val compareOrder = when(assetOrder){
            AssetAccompanimentOrderType.ASSET_SYMBOL -> compareBy(AssetModel::symbol)
            AssetAccompanimentOrderType.ASSET_NAME -> compareBy(AssetModel::displayName)
            AssetAccompanimentOrderType.ASSET_PRICE -> compareBy(AssetModel::regularMarketPrice)
        }
        return accompanimentPersistenceService.getAccompaniment(documentNumber, compareOrder)
    }

    fun addAccompaniment(documentNumber: Long) : AccompanimentModel {
        return accompanimentPersistenceService.saveAccompaniment(documentNumber)
    }

    fun updateAccompaniment(documentNumber: Long, assetSymbol: String) : AccompanimentModel {
        return accompanimentPersistenceService.updateAccompaniment(documentNumber, assetSymbol)
    }
}