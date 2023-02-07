package com.api.assettracking.dtos

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
)