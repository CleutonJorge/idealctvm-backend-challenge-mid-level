package com.api.assettracking.controllers.graphql

import com.api.assettracking.dtos.UserDTO
import com.api.assettracking.dtos.response.UserResponse
import com.api.assettracking.services.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller


@Controller
class UserResolver(
    val userService: UserService,
) {

    @MutationMapping
    fun createUser(@Argument user: UserDTO): UserResponse {
        return userService.addUser(user.documentNumber, user.fullName, user.password, user.roles)
    }

    @QueryMapping
    fun getUser(@Argument documentNumber: String): UserResponse {
        return userService.getUser(documentNumber.toLong())
    }

    @QueryMapping
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }
}