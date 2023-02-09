package com.api.assettracking.service

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.dtos.response.QuoteResponse
import com.api.assettracking.dtos.response.UserResponse
import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.repositories.UserRepository
import com.api.assettracking.services.AccompanimentService
import com.api.assettracking.services.AssetQuotationService
import com.api.assettracking.services.AssetService
import com.api.assettracking.services.UserService
import com.api.assettracking.services.persistence.AccompanimentPersistenceService
import com.api.assettracking.services.persistence.AssetPersistenceService
import com.api.assettracking.services.persistence.UserPersistenceService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class AssetServiceTests {

    @InjectMocks
    var service: AssetService? = null

    @Mock
    lateinit var assetQuotationService: AssetQuotationService

    @Mock
    lateinit var assetPersistenceService: AssetPersistenceService

    @Mock
    lateinit var accompanimentService: AccompanimentService

    @BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
        service = AssetService(assetQuotationService, assetPersistenceService, accompanimentService)
    }

    private val quoteResponse = QuoteResponse(
        name = "Ativo APPL",
        price = BigDecimal(154.5),
        symbol = "AAPL",
    )

    val createAt = LocalDateTime.now()

    private val assetResponse = AssetResponse(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(154.5)
    )

    private val accompanimentResponse = AccompanimentResponse(
        name = "Lista de ativos 01",
        createAt = createAt,
        updateAt = null,
        assetList = listOf(assetResponse)
    )

    private val assetUpdatedDAO = AssetModel(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(200.5),
        id = UUID.randomUUID()
    )

    @Test
    fun `must add asset in user list`() {

        Mockito.`when`(assetQuotationService.getAssetQuotation("AAPL"))
            .thenReturn(quoteResponse)

        Mockito.`when`(assetPersistenceService.saveAsset(
            "AAPL",
            "Ativo APPL",
            BigDecimal(154.5)
        ))
            .thenReturn(assetUpdatedDAO)

        Mockito.`when`(accompanimentService.updateAccompaniment(22400527083, "AAPL"))
            .thenReturn(accompanimentResponse)

        // execution
        val result = service?.addAssetAccompaniment(22400527083,"AAPL")

        //validation
        Assertions.assertNotNull(result)
    }
}
