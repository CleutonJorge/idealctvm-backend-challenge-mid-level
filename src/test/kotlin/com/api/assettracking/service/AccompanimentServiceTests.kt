package com.api.assettracking.service

import com.api.assettracking.models.AccompanimentModel
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

    @Test
    fun `must save accompaniment`() {
        val user = UserModel(
            documentNumber = 22400527083,
            fullName = "João Costa",
            id = UUID.randomUUID()
        )
        //Scenario
        Mockito.`when`(service?.addAccompaniment(user))
            .thenReturn(
                AccompanimentModel(
                    name = "Lista de ativos João Costa",
                    createAt = LocalDateTime.now(),
                    updateAt = null,
                    id = UUID.randomUUID()
                )
            )

		// execution
        val result = service?.addAccompaniment(user)

		//validation
		Assertions.assertNotNull(result)

    }

    @Test
    fun `must return accompaniment`() {

        Mockito.`when`(service?.getAccompaniment(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(
                AccompanimentModel(
                    name = "Lista de ativos João Costa",
                    createAt = LocalDateTime.now(),
                    updateAt = null,
                    id = UUID.randomUUID()
                )
            )

        // execution
        val result = service?.getAccompaniment(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633"))

        //validation
        Assertions.assertNotNull(result)
    }
}
