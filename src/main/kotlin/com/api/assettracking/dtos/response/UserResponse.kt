package com.api.assettracking.dtos.response

import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.security.RoleModel
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.*


class UserResponse (
    val fullName: String,
    val documentNumber: Long,
    val documentType: String,
    val roles : List<String?>
)