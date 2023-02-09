package com.api.assettracking.controllers

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.enums.AssetAccompanimentOrderType
import com.api.assettracking.services.AccompanimentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
@Tag(name = "Controller responsible for asset tracking list operations")
class AccompanimentController(
    val accompanimentService: AccompanimentService
) {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(summary = "User consults his asset tracking list")
    @GetMapping("/accompaniment/{documentNumber}/{assetOrder}")
    fun addAssetAccompaniment(@PathVariable documentNumber: Long, @PathVariable assetOrder: AssetAccompanimentOrderType): ResponseEntity<AccompanimentResponse> {
        val result = this.accompanimentService.getAccompaniment(documentNumber, assetOrder)
        return ResponseEntity.ok(result)
    }
}