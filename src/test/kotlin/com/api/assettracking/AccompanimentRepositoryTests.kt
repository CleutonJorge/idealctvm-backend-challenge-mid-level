package com.api.assettracking

import com.api.assettracking.models.AccompanimentModel
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.AccompanimentRepository
import com.api.assettracking.repositories.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*

class AccompanimentRepositoryTests {

    @Mock
    var repository: AccompanimentRepository? = null

	@BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `must save user`() {
        val dao = AccompanimentModel(
            name = "Lista de ativos 01",
            createAt = LocalDateTime.now(),
            updateAt = null,
            id = UUID.randomUUID()
        )

		// execution
        repository?.save(dao)

		//validation
		Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())

    }

    @Test
    fun `must update user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(
                Optional.of(
                    AccompanimentModel(
                        name = "Lista de ativos 01",
                        createAt = LocalDateTime.now(),
                        updateAt = null,
                        id = UUID.randomUUID()
                    )
                )
            )

        val dao = AccompanimentModel(
            name = "Lista de ativos 02",
            createAt = LocalDateTime.now(),
            updateAt = null,
            id = UUID.randomUUID()
        )

		// execution
        repository?.save(dao)

		//validation
		Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())
    }

    @Test
    fun `must return user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(
                Optional.of(
                    AccompanimentModel(
                        name = "Lista de ativos 01",
                        createAt = LocalDateTime.now(),
                        updateAt = null,
                        id = UUID.randomUUID()
                    )
                )
            )

        // execution
        val result = repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633"))

        //validation
        Assertions.assertNotNull(result)
    }

    @Test
    fun `must delete user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(
                Optional.of(
                    AccompanimentModel(
                        name = "Lista de ativos 01",
                        createAt = LocalDateTime.now(),
                        updateAt = null,
                        id = UUID.randomUUID()
                    )
                )
            )

        // execution
        val resultBeforeDeletion = repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633"))
        if (resultBeforeDeletion != null) {
            repository?.delete(resultBeforeDeletion.get())
        }

        //validation
        Assertions.assertNotNull(resultBeforeDeletion)
        Mockito.verify(repository, Mockito.atLeastOnce())?.delete(Mockito.any())
    }

}
