package com.api.assettracking.repository

import com.api.assettracking.models.AssetModel
import com.api.assettracking.models.UserModel
import com.api.assettracking.repositories.AssetRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.util.*

class AssetRepositoryTests {

    @Mock
    var repository: AssetRepository? = null

    @BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
    }

    private val assetDAO = AssetModel(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(154.5),
        id = UUID.randomUUID()
    )

    private val assetUpdatedDAO = AssetModel(
        displayName = "Ativo APPL",
        symbol = "AAPL",
        regularMarketPrice = BigDecimal(200.5),
        id = UUID.randomUUID()
    )

    @Test
    fun `must save user`() {

        // execution
        repository?.save(assetDAO)

        //validation
        Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())

    }

    @Test
    fun `must update user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(assetDAO))

        // execution
        repository?.save(assetUpdatedDAO)

        //validation
        Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())
    }

    @Test
    fun `must return user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(assetDAO))

        // execution
        val result = repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633"))

        //validation
        Assertions.assertNotNull(result)
    }

    @Test
    fun `must delete user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(assetDAO))

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
