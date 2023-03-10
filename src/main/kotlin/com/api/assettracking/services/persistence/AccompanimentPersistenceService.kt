package com.api.assettracking.services.persistence

import com.api.assettracking.exceptions.UserAccompanimentNotExistException
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.repositories.AccompanimentRepository
import com.api.assettracking.repositories.AssetRepository
import com.api.assettracking.repositories.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AccompanimentPersistenceService(
    val accompanimentRepository: AccompanimentRepository,
    val userPersistenceService: UserPersistenceService,
    val assetPersistenceService: AssetPersistenceService
) {

    fun saveAccompaniment(documentNumber: Long): AccompanimentModel {
        val user = userPersistenceService.getUser(documentNumber)
        return accompanimentRepository.save(
            AccompanimentModel(
                name = "Lista de acompanhamento " + user.fullName,
                createAt = LocalDateTime.now(),
                updateAt = null,
                user = user
            )
        )
    }

    fun getAccompaniment(documentNumber: Long): AccompanimentModel {
        val user = userPersistenceService.getUser(documentNumber)
        val accompanimentOptional = accompanimentRepository.findByUser(user)
        return verifyingAccompanimentIsPresent(accompanimentOptional)
    }

    fun updateAccompaniment(documentNumber: Long, assetSymbol: String): AccompanimentModel {
        val user = userPersistenceService.getUser(documentNumber)
        val newAsset = assetPersistenceService.findAssetBySymbol(assetSymbol)
        val userAccompanimentOptional = accompanimentRepository.findByUser(user)
        val userAccompaniment = verifyingAccompanimentIsPresent(userAccompanimentOptional)
        val userAssetsConcatenate = concatenatesUserNewAsset(userAccompaniment, newAsset)
        return accompanimentRepository.save(
            AccompanimentModel(
                name = userAccompaniment.name,
                createAt = userAccompaniment.createAt,
                updateAt = LocalDateTime.now(),
                assets = userAssetsConcatenate,
                id = userAccompaniment.id,
                user = user
            )
        )
    }

    private fun verifyingAccompanimentIsPresent(accompaniment: Optional<AccompanimentModel>): AccompanimentModel {
        return when (accompaniment.isEmpty) {
            true -> throw UserAccompanimentNotExistException("user accompaniment not exist")
            false -> accompaniment.get()
        }
    }

    private fun concatenatesUserNewAsset(
        userActualAccompaniment: AccompanimentModel,
        newAsset: AssetModel
    ): MutableList<AssetModel> {
        return when (userActualAccompaniment.assets.any { it.symbol == newAsset.symbol }) {
            true -> userActualAccompaniment.assets
            false -> userActualAccompaniment.assets.plus(newAsset)
        }.toMutableList()
    }
}