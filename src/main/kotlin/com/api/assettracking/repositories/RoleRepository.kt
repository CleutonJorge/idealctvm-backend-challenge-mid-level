package com.api.assettracking.repositories

import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<RoleModel, UUID> {
    fun findByRoleName(roleName: RoleName) : Optional<RoleModel>
}