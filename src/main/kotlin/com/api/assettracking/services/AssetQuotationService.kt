package com.api.assettracking.services

import com.api.assettracking.exceptions.AsserQuotationNotExistException
import com.api.assettracking.dtos.response.AssetQuotationResponse
import com.api.assettracking.dtos.response.QuoteResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

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