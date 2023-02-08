package com.api.assettracking.controllers

import com.api.assettracking.dtos.UserDTO
import com.api.assettracking.exceptions.UserRegisteredException
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class UserController(
    val userService: UserService
) {
    @PostMapping("/user")
    fun createUser(@RequestBody @Valid user: UserDTO): ResponseEntity<UserModel> {
        val result = this.userService.addUser(user.documentNumber, user.fullName)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/user/{documentNumber}")
    fun getUser(@PathVariable documentNumber: Long): ResponseEntity<UserModel> {
        val result = this.userService.getUser(documentNumber)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/user/all/user-list")
    fun getUsers(): ResponseEntity<List<UserModel>> {
        val result = this.userService.getUsers()
        return ResponseEntity.ok(result)
    }
}