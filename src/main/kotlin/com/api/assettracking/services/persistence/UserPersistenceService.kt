package com.api.assettracking.services.persistence

import com.api.assettracking.enums.RoleName
import com.api.assettracking.exceptions.UserAccompanimentNotExistException
import com.api.assettracking.exceptions.UserNotRegisteredException
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.UserRepository
import com.api.assettracking.exceptions.UserRegisteredException
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.repositories.RoleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserPersistenceService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository
) {

    fun saveUser(documentNumber: Long, fullName: String, documentType: String, password: String, roles : List<RoleName>): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        return when(user.isEmpty) {
                true -> {
                    val saveOrGetRoles = saveOrGetRoles(roles)
                    userRepository.save(
                        UserModel(
                            documentNumber = documentNumber,
                            fullName = fullName,
                            id = UUID.randomUUID(),
                            type = documentType,
                            password = password,
                            roles = saveOrGetRoles
                        )
                    )
                }
            false -> throw UserRegisteredException("the user is already registered")
        }
    }

    fun saveOrGetRoles(roles : List<RoleName>): List<RoleModel> {
        return roles.map {
            val role = roleRepository.findByRoleName(it)
            if (role.isEmpty) roleRepository.save(RoleModel(roleName = it)) else role.get()
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