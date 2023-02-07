package com.api.assettracking.services.persistence

import com.api.assettracking.exceptions.UserAccompanimentNotExistException
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.AssetQuotationResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.AccompanimentRepository
import com.api.assettracking.repositories.AssetRepository
import com.api.assettracking.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
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

    fun getAccompaniment(documentNumber: Long): AccompanimentModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        val accompaniment = accompanimentRepository.findByUser(user.get())
        if (accompaniment.isEmpty) {
            throw UserAccompanimentNotExistException("user accompaniment not exist")
        } else return accompaniment.get()
    }

    fun updateAccompaniment(documentNumber: Long, assetSymbol: String): AccompanimentModel {
        val user = userRepository.findByDocumentNumber(documentNumber).get()
        val asset = assetRepository.findBySymbol(assetSymbol).get()

        val accompaniment = accompanimentRepository.findByUser(user)
        val newAssets = when (accompaniment.get().assets.any { it.symbol == asset.symbol }) {
            true -> accompaniment.get().assets
            false -> accompaniment.get().assets.plus(asset)
        }

        if (accompaniment.isEmpty) {
            throw UserAccompanimentNotExistException("user accompaniment not exist")
        } else {
            return accompanimentRepository.save(
                AccompanimentModel(
                    name = accompaniment.get().name,
                    createAt = accompaniment.get().createAt,
                    updateAt = LocalDateTime.now(),
                    assets = newAssets,
                    id = accompaniment.get().id,
                    user = user
                )
            )
        }
    }

    fun deleteAccompaniment(id: UUID) {
        val accompaniment = accompanimentRepository.findById(id)
        if (accompaniment.isEmpty) {
            throw UserAccompanimentNotExistException("user accompaniment not exist")
        } else {
            accompanimentRepository.delete(accompaniment.get())
        }
    }

}