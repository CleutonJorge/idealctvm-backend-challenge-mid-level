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
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class AssetQuotationService(
    val template: RestTemplate,
) {

    fun getAssetQuotation(asset: String): QuoteResponse {
        val uri = getUri(asset)
        val headers = getHeaders()
        val entity = template.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            HttpEntity<Any>(headers),
            AssetQuotationResponse::class.java
        )
        return entity.body?.quoteResponse?.result?.firstOrNull()
            ?: throw AsserQuotationNotExistException("the asset does not exist: $asset")
    }

    fun getUri(asset: String): UriComponents {
        return UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("yfapi.net")
            .path("v6/finance/quote")
            .queryParam("region", "US")
            .queryParam("lang", "en")
            .queryParam("symbols", asset)
            .build()
    }

    fun getHeaders(): MultiValueMap<String, String> {
        val headers: MultiValueMap<String, String> = LinkedMultiValueMap()
        headers.add("X-API-KEY", "AfMRqDhPiT8H5fKs0oTKe7kB4HLHJQi36429ZoZM")
        return headers
    }

}