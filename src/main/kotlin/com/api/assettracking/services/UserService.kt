package com.api.assettracking.services

import com.api.assettracking.enums.DocumentType
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.persistence.UserPersistenceService
import org.springframework.stereotype.Service

@Service
class UserService(
    val userPersistenceService: UserPersistenceService,
    val accompanimentService: AccompanimentService
) {

    fun getUser(documentNumber: Long): UserModel {
        return userPersistenceService.getUser(documentNumber)
    }

    fun getUsers(): List<UserModel> {
        return userPersistenceService.getUsers()
    }

    fun addUser(documentNumber: Long, fullName: String): UserModel {
        val documentType = getDocumentType(documentNumber)
        val user = userPersistenceService.saveUser(documentNumber, fullName, documentType.name)
        accompanimentService.addAccompaniment(user.documentNumber)
        return user
    }

    private fun getDocumentType(documentNumber: Long): DocumentType {
        return when (documentNumber.toString().length == 11) {
            true -> DocumentType.CPF
            false -> DocumentType.CNPJ
        }
    }
}