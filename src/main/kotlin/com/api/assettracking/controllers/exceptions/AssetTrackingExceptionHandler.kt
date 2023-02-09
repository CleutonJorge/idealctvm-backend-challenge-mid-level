package com.api.assettracking.controllers.exceptions

import com.api.assettracking.controllers.exceptions.ApiError
import com.api.assettracking.exceptions.AsserQuotationNotExistException
import com.api.assettracking.exceptions.UserAccompanimentNotExistException
import com.api.assettracking.exceptions.UserNotRegisteredException
import com.api.assettracking.exceptions.UserRegisteredException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

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

    @ExceptionHandler(AsserQuotationNotExistException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleAsserQuotationNotExistException(ex: AsserQuotationNotExistException?): ApiError? {
        return ApiError(
            status = HttpStatus.NOT_FOUND,
            statusValue = HttpStatus.NOT_FOUND.value().toString(),
            error = ex?.message,
        )
    }

    @ExceptionHandler(UserAccompanimentNotExistException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleUserAccompanimentNotExistException(ex: UserAccompanimentNotExistException?): ApiError? {
        return ApiError(
            status = HttpStatus.NOT_FOUND,
            statusValue = HttpStatus.NOT_FOUND.value().toString(),
            error = ex?.message,
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException?): ApiError? {
        return ApiError(
            status = HttpStatus.BAD_REQUEST,
            statusValue = HttpStatus.BAD_REQUEST.value().toString(),
            error = ex?.message,
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException?): ApiError? {
        return ApiError(
            status = HttpStatus.BAD_REQUEST,
            statusValue = HttpStatus.BAD_REQUEST.value().toString(),
            error = ex?.message,
        )
    }
}