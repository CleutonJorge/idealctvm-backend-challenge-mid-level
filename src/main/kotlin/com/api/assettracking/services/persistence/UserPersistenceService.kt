package com.api.assettracking.services.persistence

import com.api.assettracking.exceptions.UserNotRegisteredException
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.UserRepository
import com.api.assettracking.exceptions.UserRegisteredException
import org.springframework.stereotype.Service
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
        } else throw UserRegisteredException("the user is already registered")
    }

    fun getUser(documentNumber: Long): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw UserNotRegisteredException("the user is not registered")
        } else return user.get()
    }

    fun updateUser(documentNumber: Long, fullName: String): UserModel {
        val user = userRepository.findByDocumentNumber(documentNumber)
        if (user.isEmpty) {
            throw UserRegisteredException("the user is already registered")
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
            throw UserNotRegisteredException("the user is not registered")
        } else {
            userRepository.delete(user.get())
        }
    }

}