package com.api.assettracking.service

import com.api.assettracking.models.UserModel
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

    @Test
    fun `must save user`() {
        val dao = UserModel(
            documentNumber = 22400527083,
            fullName = "João Costa",
            id = UUID.randomUUID()
        )
        //Scenario
        Mockito.`when`(service?.addUser(22400527083, "João Costa"))
            .thenReturn(
                    UserModel(
                        documentNumber = 22400527083,
                        fullName = "João Costa",
                        id = UUID.randomUUID()
                    )
            )

		// execution
        val result = service?.addUser(22400527083, "João Costa")

		//validation
		Assertions.assertNotNull(result)

    }

    @Test
    fun `must return user`() {

        Mockito.`when`(service?.getUser(22400527083))
            .thenReturn(
                    UserModel(
                        documentNumber = 22400527083,
                        fullName = "João Costa",
                        id = UUID.randomUUID()
                    )
            )

        // execution
        val result = service?.getUser(22400527083)

        //validation
        Assertions.assertNotNull(result)
    }
}
