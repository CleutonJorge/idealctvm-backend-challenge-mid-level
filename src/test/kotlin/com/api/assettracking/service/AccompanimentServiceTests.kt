package com.api.assettracking.service

import com.api.assettracking.enums.AssetAccompanimentOrderType
import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.repositories.UserRepository
import com.api.assettracking.services.AccompanimentService
import com.api.assettracking.services.UserService
import com.api.assettracking.services.persistence.AccompanimentPersistenceService
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

    val roleDAO = RoleModel(
        roleName = RoleName.ROLE_USER
    )

    val passwordEncrypted = BCryptPasswordEncoder().encode("123")

    private val userDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa",
        id = UUID.randomUUID(),
        password = passwordEncrypted,
        roles = listOf(roleDAO)
    )
    private val accompanimentDAO = AccompanimentModel(
        name = "Lista de ativos 01",
        createAt = LocalDateTime.now(),
        updateAt = null,
        id = UUID.randomUUID(),
        user = userDAO
    )

    @Test
    fun `must save accompaniment`() {
        //Scenario
        Mockito.`when`(service?.addAccompaniment(22400527083))
            .thenReturn(accompanimentDAO)

        // execution
        val result = service?.addAccompaniment(22400527083)

        //validation
        Assertions.assertNotNull(result)

    }

}
