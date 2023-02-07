package com.api.assettracking.services

import com.api.assettracking.enums.DocumentType
import com.api.assettracking.exceptions.AsserQuotationNotExistException
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.AssetQuotationResponse
import com.api.assettracking.models.QuoteResponse
import com.api.assettracking.models.UserModel
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
class AssetService(
    val assetQuotationService: AssetQuotationService,
    val assetPersistenceService: AssetPersistenceService,
    val accompanimentService: AccompanimentService,
) {
    fun addAssetAccompaniment(documentNumber: Long, assetSymbol: String) : AssetModel {
        val assetQuotation = assetQuotationService.getAssetQuotation(assetSymbol)
        val asset = assetPersistenceService.saveAsset(
            assetQuotation.symbol,
            assetQuotation.shortName,
            assetQuotation.regularMarketPrice)
        accompanimentService.updateAccompaniment(documentNumber, assetSymbol)
        return asset
    }

}