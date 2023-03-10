package com.api.assettracking.service

import com.api.assettracking.dtos.response.AccompanimentResponse
import com.api.assettracking.dtos.response.AssetResponse
import com.api.assettracking.dtos.response.UserResponse
import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.AccompanimentModel
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
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class UserServiceTests {

    @InjectMocks
    var service: UserService? = null

    @Mock
    lateinit var userPersistenceService: UserPersistenceService

    @Mock
    lateinit var accompanimentService: AccompanimentService

    @BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
        service = UserService(userPersistenceService, accompanimentService)
    }

    private val roleDAO = RoleModel(
        roleName = RoleName.ROLE_USER
    )

    private val passwordEncrypted = BCryptPasswordEncoder().encode("")

    val id = UUID.randomUUID()

    val userDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa",
        id = id,
        password = passwordEncrypted,
        type = "CPF",
        roles = listOf(roleDAO)
    )

    val createAt = LocalDateTime.now()

    private val assetResponse = AssetResponse(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(154.5)
    )

    //TODO error occurs when trying to modify BCryptPasswordEncoder
    /*@Test
    fun `must save user`() {
        //Scenario

        Mockito.doReturn(userDAO)
            .`when`(userPersistenceService)
            .saveUser(22400527083, "João Costa", "CPF", passwordEncrypted, listOf(RoleName.ROLE_USER))

        Mockito.`when`(accompanimentService.addAccompaniment(22400527083))
            .thenReturn(accompanimentResponse)

        // execution
        val result = service?.addUser(22400527083, "João Costa", "", listOf(RoleName.ROLE_USER))

        //validation
        Assertions.assertNotNull(result)

    }*/

    @Test
    fun `must return user`() {

        Mockito.`when`(userPersistenceService.getUser(22400527083))
            .thenReturn(userDAO)

        // execution
        val result = service?.getUser(22400527083)

        //validation
        Assertions.assertNotNull(result)
    }

    @Test
    fun `must return users`() {

        Mockito.`when`(userPersistenceService.getUsers())
            .thenReturn(listOf(userDAO))

        // execution
        val result = service?.getUsers()

        //validation
        Assertions.assertNotNull(result)
    }
}
