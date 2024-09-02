package com.api.assettracking.externals

import com.api.assettracking.dtos.response.QuoteResponse
import com.api.assettracking.externals.response.YahooFinancialQuotationResponse
import com.api.assettracking.interfaces.AssetQuotationInterface
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.math.BigDecimal

@Component
class YahooFinancialQuotes (
    private val template: RestTemplate
): AssetQuotationInterface {

    override fun getExternalQuotation(asset: String): QuoteResponse? {
        val uri = getUri(asset)
        val headers = getHeaders()
        getHeaders()
        val response = template.exchange(
            uri.toUriString(),
            HttpMethod.GET,
            HttpEntity<Any>(headers),
            YahooFinancialQuotationResponse::class.java
        ).body?.quoteResponse?.result?.firstOrNull()
        return if (response != null) {
            QuoteResponse(
                name = response.shortName ?: ("Ativo " + response.symbol),
                symbol = response.symbol,
                price = response.regularMarketPrice ?: BigDecimal.ZERO
            )
        } else null
    }

    private fun getUri(asset: String): UriComponents {
        return UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("yfapi.net")
            .path("v6/finance/quote")
            .queryParam("region", "US")
            .queryParam("lang", "en")
            .queryParam("symbols", asset)
            .build()
    }

    private fun getHeaders(): MultiValueMap<String, String> {
        val headers: MultiValueMap<String, String> = LinkedMultiValueMap()
        headers.add("X-API-KEY", "BCcVexig011kebh8jcMtO49ajBXaihGr6iefikLY")
        return headers
    }
}