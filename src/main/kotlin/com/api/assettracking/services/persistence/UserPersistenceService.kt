package com.api.assettracking.services.persistence

import com.api.assettracking.models.AssetQuotationResponse
import com.api.assettracking.models.QuoteResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class UserPersistenceService(
    val userRepository: UserRepository
) {

    fun saveUser(documentNumber: Long, fullName: String, documentType: String): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            return userRepository.save(
                UserModel(
                    documentNumber = documentNumber,
                    fullName = fullName,
                    id = UUID.randomUUID(),
                    type = documentType
                )
            )
        } else throw Exception("the user is already registered")
    }

    fun getUser(documentNumber: Long): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw Exception("the user is not registered")
        } else return user.get()
    }

    fun updateUser(documentNumber: Long, fullName: String): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw Exception("the user is already registered")
        } else {
            return userRepository.save(
                UserModel(
                    documentNumber = user.get().documentNumber,
                    fullName = fullName,
                    id = user.get().id
                )
            )
        }
    }

    fun deleteUser(documentNumber: Long) {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw Exception("the user is not registered")
        } else {
            userRepository.delete(user.get())
        }
    }

}