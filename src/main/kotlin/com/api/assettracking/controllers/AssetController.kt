package com.api.assettracking.controllers

import com.api.assettracking.dtos.AssetDTO
import com.api.assettracking.dtos.AssetListQuotationDTO
import com.api.assettracking.dtos.UserDTO
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.QuoteResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.AssetQuotationService
import com.api.assettracking.services.AssetService
import com.api.assettracking.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class AssetController(
    val assetService: AssetService,
    val assetQuotationService: AssetQuotationService
) {
    //1. Usuário adiciona um ativo em sua lista de acompanhamento.
    @PostMapping("/asset")
    fun addAssetAccompaniment(@RequestBody asset: AssetDTO): ResponseEntity<AssetModel> {
        val result = this.assetService.addAssetAccompaniment(asset.documentNumber, asset.symbol)
        return ResponseEntity.ok(result)
    }

    //4. Usuário consulta a cotação de um ou mais ativos.
    @GetMapping("/asset/asset-list")
    fun getAssetListQuotation(@RequestBody assetList: AssetListQuotationDTO): ResponseEntity<List<QuoteResponse>> {
        val result = assetList.assetList.map { this.assetQuotationService.getAssetQuotation(it) }
        return ResponseEntity.ok(result)
    }
}