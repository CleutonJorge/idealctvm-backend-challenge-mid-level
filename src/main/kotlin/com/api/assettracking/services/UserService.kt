package com.api.assettracking.services

import com.api.assettracking.enums.DocumentType
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.AssetQuotationResponse
import com.api.assettracking.models.QuoteResponse
import com.api.assettracking.models.UserModel
import com.api.assettracking.services.persistence.AccompanimentPersistenceService
import com.api.assettracking.services.persistence.AssetPersistenceService
import com.api.assettracking.services.persistence.UserPersistenceService
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class UserService(
    val userPersistenceService: UserPersistenceService,
    val accompanimentService: AccompanimentService
) {

    fun getUser(documentNumber: Long) : UserModel {
        return userPersistenceService.getUser(documentNumber)
    }

    fun addUser(documentNumber: Long, fullName: String) : UserModel {
        val documentType = if (documentNumber.toString().length == 11) DocumentType.CPF else DocumentType.CNPJ
        val user = userPersistenceService.saveUser(documentNumber, fullName, documentType.name)
        accompanimentService.addAccompaniment(user.documentNumber)
        return user
    }
}