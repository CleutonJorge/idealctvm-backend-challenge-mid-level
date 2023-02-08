package com.api.assettracking.services.persistence

import com.api.assettracking.enums.RoleName
import com.api.assettracking.exceptions.UserAccompanimentNotExistException
import com.api.assettracking.exceptions.UserNotRegisteredException
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.UserRepository
import com.api.assettracking.exceptions.UserRegisteredException
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.security.RoleModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserPersistenceService(
    val userRepository: UserRepository
) {

    fun saveUser(documentNumber: Long, fullName: String, documentType: String, password: String, roles : List<RoleName>): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        return when(user.isEmpty) {
            true -> userRepository.save(
                UserModel(
                    documentNumber = documentNumber,
                    fullName = fullName,
                    id = UUID.randomUUID(),
                    type = documentType,
                    password = password,
                    roles = roles.map { RoleModel( roleName = it ) }
                )
            )
            false -> throw UserRegisteredException("the user is already registered")
        }
    }

    fun getUser(documentNumber: Long): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw UserNotRegisteredException("the user is not registered")
        } else return user.get()
    }

    fun getUsers(): List<UserModel> {
        return userRepository.findAll().map { it }
    }

}