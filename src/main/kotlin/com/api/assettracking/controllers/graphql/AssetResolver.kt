package com.api.assettracking.controllers.graphql

import com.api.assettracking.dtos.AssetDTO
import com.api.assettracking.dtos.AssetListQuotationDTO
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.dtos.response.QuoteResponse
import com.api.assettracking.services.AssetQuotationService
import com.api.assettracking.services.AssetService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AssetResolver(
    val assetService: AssetService,
    val assetQuotationService: AssetQuotationService
) {
    @MutationMapping
    fun addAssetAccompaniment(@Argument asset: AssetDTO): AssetResponse {
        return assetService.addAssetAccompaniment(asset.documentNumber, asset.symbol)
    }

    @QueryMapping
    fun getAssetListQuotation(@Argument assetList: AssetListQuotationDTO): List<QuoteResponse> {
        return assetList.assetList.map { assetQuotationService.getAssetQuotation(it) }
    }
}