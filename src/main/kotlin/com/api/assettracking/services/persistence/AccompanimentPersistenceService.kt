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
    val userRepository: UserRepository,
    val assetRepository: AssetRepository
) {

    fun saveAccompaniment(documentNumber: Long): AccompanimentModel {
        val user = userRepository.findByDocumentNumber(documentNumber).get()
        return accompanimentRepository.save(
            AccompanimentModel(
                name = "Lista de acompanhamento " + user.fullName,
                createAt = LocalDateTime.now(),
                updateAt = null,
                user = user
            )
        )
    }

    fun getAccompaniment(documentNumber: Long, compareOrder: Comparator<AssetModel>): AccompanimentModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        val accompanimentOptional = accompanimentRepository.findByUser(user.get())
        val accompaniment = verifyingAccompanimentIsPresent(accompanimentOptional)
        return sortUserAssetList(accompaniment, compareOrder)
    }

    fun updateAccompaniment(documentNumber: Long, assetSymbol: String): AccompanimentModel {
        val user = userRepository.findByDocumentNumber(documentNumber).get()
        val newAsset = assetRepository.findBySymbol(assetSymbol).get()
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

    private fun sortUserAssetList(
        accompaniment: AccompanimentModel,
        compareOrder: Comparator<AssetModel>
    ): AccompanimentModel {
        val assets = accompaniment.assets.sortedWith(compareOrder)
        accompaniment.assets = assets.toMutableList()
        return accompaniment
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