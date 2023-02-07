package com.api.assettracking.repositories

import com.api.assettracking.models.AssetModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AssetRepository : JpaRepository<AssetModel, UUID> {
    fun findBySymbol(symbol: String) : Optional<AssetModel>
}