package com.api.assettracking.services

import com.api.assettracking.enums.DocumentType
import com.api.assettracking.models.*
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
class AccompanimentService(
    val accompanimentPersistenceService: AccompanimentPersistenceService,
) {

    fun getAccompaniment(id: UUID) : AccompanimentModel {
        return accompanimentPersistenceService.getAccompaniment(id)
    }

    fun addAccompaniment(user: UserModel) : AccompanimentModel {
        return accompanimentPersistenceService.saveAccompaniment(user)
    }
}