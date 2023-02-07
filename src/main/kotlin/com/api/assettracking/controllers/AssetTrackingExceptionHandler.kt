package com.api.assettracking.controllers

import com.api.assettracking.exceptions.UserNotRegisteredException
import com.api.assettracking.exceptions.UserRegisteredException
import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AssetTrackingExceptionHandler {

    @ExceptionHandler(UserRegisteredException::class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    fun handleUserRegisteredException(ex: UserRegisteredException?): ApiError? {
        return ApiError(
            status = HttpStatus.CONFLICT,
            statusValue = HttpStatus.CONFLICT.value().toString(),
            error = ex?.message,
        )
    }

    @ExceptionHandler(UserNotRegisteredException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleUserNotRegisteredException(ex: UserNotRegisteredException?): ApiError? {
        return ApiError(
            status = HttpStatus.NOT_FOUND,
            statusValue = HttpStatus.NOT_FOUND.value().toString(),
            error = ex?.message,
        )
    }
}