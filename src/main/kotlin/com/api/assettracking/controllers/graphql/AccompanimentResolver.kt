package com.api.assettracking.controllers.graphql

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.enums.AssetAccompanimentOrderType
import com.api.assettracking.services.AccompanimentService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AccompanimentResolver(
    val accompanimentService: AccompanimentService
) {
    @QueryMapping
    fun getAssetAccompaniment(
        @Argument documentNumber: String,
        @Argument assetOrder: AssetAccompanimentOrderType
    ): AccompanimentResponse {
        return accompanimentService.getAccompaniment(documentNumber.toLong(), assetOrder)
    }
}