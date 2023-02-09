package com.api.assettracking.services

import com.api.assettracking.dtos.response.UserResponse
import com.api.assettracking.enums.DocumentType
import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.services.persistence.UserPersistenceService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userPersistenceService: UserPersistenceService,
    val accompanimentService: AccompanimentService
) {

    fun getUser(documentNumber: Long): UserResponse {
        return userPersistenceService.getUser(documentNumber).toResponse()
    }

    fun getUsers(): List<UserResponse> {
        return userPersistenceService.getUsers().map { it.toResponse() }
    }

    fun addUser(documentNumber: Long, fullName: String, password: String, roles : List<RoleName>): UserResponse {
        val documentType = getDocumentType(documentNumber)
        val passwordEncrypted = BCryptPasswordEncoder().encode(password)
        val user = userPersistenceService.saveUser(documentNumber, fullName, documentType.name, passwordEncrypted, roles)
        accompanimentService.addAccompaniment(user.documentNumber)
        return user.toResponse()
    }

    private fun getDocumentType(documentNumber: Long): DocumentType {
        return when (documentNumber.toString().length == 11) {
            true -> DocumentType.CPF
            false -> DocumentType.CNPJ
        }
    }

    private fun UserModel.toResponse() : UserResponse {
        return UserResponse(
            documentNumber = documentNumber,
            documentType = type,
            fullName = fullName,
            roles = authorities.map { it?.authority }
        )
    }
}