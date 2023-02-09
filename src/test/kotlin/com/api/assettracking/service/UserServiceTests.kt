package com.api.assettracking.service

import com.api.assettracking.dtos.response.AccompanimentResponse
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

    private val passwordEncrypted: String = BCryptPasswordEncoder().encode("123")

    private val userResponse = UserResponse(
        documentNumber = 22400527083,
        fullName = "João Costa",
        documentType = "CPF",
        roles = listOf("ROLE_USER")
    )

    val userDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa",
        id = UUID.randomUUID(),
        password = passwordEncrypted,
        type = "CPF",
        roles = listOf(roleDAO)
    )

    /*@Test
    fun `must save user`() {
        //Scenario

        Mockito.`when`(userPersistenceService.saveUser(22400527083, "João Costa", "CPF", passwordEncrypted, listOf(RoleName.ROLE_USER)))
            .thenReturn(userDAO)

        Mockito.`when`(accompanimentService.addAccompaniment(22400527083))
            .thenReturn(accompanimentDAO)

        // execution
        val result = service?.addUser(22400527083, "João Costa", "123", listOf(RoleName.ROLE_USER))

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
}
