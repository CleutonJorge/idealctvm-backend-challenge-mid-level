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
    val template: RestTemplate,
    val userService: UserService,
    val assetPersistenceService: AssetPersistenceService,
    val accompanimentService: AccompanimentService,
) {

    fun getAssetQuotation(asset: String) : QuoteResponse {
        val uri = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("yfapi.net")
            .path("v6/finance/quote")
            .queryParam("region", "US")
            .queryParam("lang", "en")
            .queryParam("symbols", asset)
            .build()

        val headers: MultiValueMap<String, String> = LinkedMultiValueMap()
        headers.add("X-API-KEY", "AfMRqDhPiT8H5fKs0oTKe7kB4HLHJQi36429ZoZM");

        val entity = template.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            HttpEntity<Any>(headers),
            AssetQuotationResponse::class.java
        )

        return entity.body?.quoteResponse?.result?.firstOrNull() ?:
        throw AsserQuotationNotExistException("the asset does not exist: $asset")
    }

    fun addAssetAccompaniment(documentNumber: Long, assetSymbol: String) : AssetModel {
        val assetQuotation = getAssetQuotation(assetSymbol)
        val asset = assetPersistenceService.saveAsset(
            assetQuotation.symbol,
            assetQuotation.shortName,
            assetQuotation.regularMarketPrice)
        accompanimentService.updateAccompaniment(documentNumber, assetSymbol)
        return asset
    }

}