package com.api.assettracking.dtos

import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.security.RoleModel
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.*


class UserDTO (
    @NotBlank
    @Size(max = 70)
    val fullName: String,
    @NotBlank
    @Size(max = 14)
    val documentNumber: Long,
    @Size(max = 30)
    val password: String,
    val roles : List<RoleName>
)