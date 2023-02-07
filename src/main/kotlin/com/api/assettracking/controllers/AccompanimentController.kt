package com.api.assettracking.controllers

import com.api.assettracking.enums.AssetAccompanimentOrderType
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.services.AccompanimentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class AccompanimentController(
    val accompanimentService: AccompanimentService
) {
    //3. Usuário consulta sua lista de acompanhamento.
    @GetMapping("/accompaniment/{documentNumber}/{assetOrder}")
    fun addAssetAccompaniment(@PathVariable documentNumber: Long, @PathVariable assetOrder: AssetAccompanimentOrderType): ResponseEntity<AccompanimentModel> {
        val result = this.accompanimentService.getAccompaniment(documentNumber, assetOrder)
        return ResponseEntity.ok(result)
    }
}