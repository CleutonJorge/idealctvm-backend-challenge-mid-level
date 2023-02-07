package com.api.assettracking.controllers

import com.api.assettracking.dtos.AssetDTO
import com.api.assettracking.dtos.AssetListQuotationDTO
import com.api.assettracking.dtos.UserDTO
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.QuoteResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.AccompanimentService
import com.api.assettracking.services.AssetService
import com.api.assettracking.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class AccompanimentController(
    val accompanimentService: AccompanimentService
) {
    //3. Usuário consulta sua lista de acompanhamento.
    @GetMapping("/accompaniment/{documentNumber}")
    fun addAssetAccompaniment(@PathVariable documentNumber: Long): ResponseEntity<AccompanimentModel> {
        val result = this.accompanimentService.getAccompaniment(documentNumber)
        return ResponseEntity.ok(result)
    }
}