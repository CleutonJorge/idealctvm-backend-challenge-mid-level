package com.api.assettracking.controllers.rest

import com.api.assettracking.dtos.AssetDTO
import com.api.assettracking.dtos.AssetListQuotationDTO
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.dtos.response.QuoteResponse
import com.api.assettracking.services.AssetQuotationService
import com.api.assettracking.services.AssetService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
@Tag(name = "Controller responsible for asset management")
class AssetController(
    val assetService: AssetService,
    val assetQuotationService: AssetQuotationService
) {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(summary = "User add an asset to their asset tracking list")
    @PostMapping("/asset")
    fun addAssetAccompaniment(@RequestBody asset: AssetDTO): ResponseEntity<AssetResponse> {
        val result = this.assetService.addAssetAccompaniment(asset.documentNumber, asset.symbol)
        return ResponseEntity.ok(result)
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(summary = "User consults the quotation of one or more assets")
    @GetMapping("/asset/asset-list")
    fun getAssetListQuotation(@RequestBody assetList: AssetListQuotationDTO): ResponseEntity<List<QuoteResponse>> {
        val result = assetList.assetList.map { this.assetQuotationService.getAssetQuotation(it) }
        return ResponseEntity.ok(result)
    }
}