package com.api.assettracking.repositories

import com.api.assettracking.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserModel, UUID> {
    fun findByDocumentNumber(documentNumber: Long) : Optional<UserModel>
}