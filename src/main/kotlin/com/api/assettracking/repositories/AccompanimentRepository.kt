package com.api.assettracking.repositories

import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccompanimentRepository : JpaRepository<AccompanimentModel, UUID>{
    fun findByUser(user: UserModel): Optional<AccompanimentModel>
}