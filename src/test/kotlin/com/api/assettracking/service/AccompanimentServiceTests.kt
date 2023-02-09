package com.api.assettracking.service

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.services.AccompanimentService
import com.api.assettracking.services.persistence.AccompanimentPersistenceService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class AccompanimentServiceTests {

    @InjectMocks
    var service: AccompanimentService? = null

    @Mock
    lateinit var accompanimentPersistenceService: AccompanimentPersistenceService

    @BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
        service = AccompanimentService(accompanimentPersistenceService)
    }

    private val roleDAO = RoleModel(
        roleName = RoleName.ROLE_USER
    )

    val createAt = LocalDateTime.now()
    val id = UUID.randomUUID()

    private val passwordEncrypted: String = BCryptPasswordEncoder().encode("123")

    private val assetResponse = AssetResponse(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(154.5)
    )

    private val assetDAO = AssetModel(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(154.5),
        id = UUID.randomUUID()
    )

    val userDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa Silva",
        id = UUID.randomUUID(),
        password = passwordEncrypted,
        type = "CPF",
        roles = listOf(roleDAO)
    )

    private val accompanimentResponse = AccompanimentResponse(
        name = "Lista de ativos 01",
        createAt = createAt,
        updateAt = null,
        assetList = listOf(assetResponse)
    )

    val accompanimentDAO = AccompanimentModel(
        name = "Lista de ativos 01",
        createAt = createAt,
        updateAt = null,
        id = id,
        user = userDAO,
        assets = mutableListOf(assetDAO)
    )

    fun AccompanimentModel.toResponse() : AccompanimentResponse {
        return AccompanimentResponse(
            name = name,
            createAt = createAt,
            updateAt = updateAt,
            assetList = assets.map { AssetResponse(
                displayName = it.displayName,
                regularMarketPrice = it.regularMarketPrice,
                symbol = it.symbol
            ) }
        )
    }

    @Test
    fun `must save accompaniment`() {
        //Scenario
        Mockito.doReturn(accompanimentDAO).`when`(accompanimentPersistenceService).saveAccompaniment(22400527083)

        // execution
        val result = service?.addAccompaniment(22400527083)

        //validation
        Assertions.assertNotNull(result)

    }

}
