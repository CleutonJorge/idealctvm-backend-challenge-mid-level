package com.api.assettracking.controllers

import com.api.assettracking.dtos.UserDTO
import com.api.assettracking.exceptions.UserRegisteredException
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
@Tag(name = "Controller responsible for user management")
class UserController(
    val userService: UserService
) {
    @Operation(summary = "Add a new user to the system")
    @PostMapping("/user")
    fun createUser(@RequestBody @Valid user: UserDTO): ResponseEntity<UserModel> {
        val result = this.userService.addUser(user.documentNumber, user.fullName, user.password, user.roles)
        return ResponseEntity.ok(result)
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Search for a user in the system")
    @GetMapping("/user/{documentNumber}")
    fun getUser(@PathVariable documentNumber: Long): ResponseEntity<UserModel> {
        val result = this.userService.getUser(documentNumber)
        return ResponseEntity.ok(result)
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "List all registered users")
    @GetMapping("/user/all/user-list")
    fun getUsers(): ResponseEntity<List<UserModel>> {
        val result = this.userService.getUsers()
        return ResponseEntity.ok(result)
    }
}