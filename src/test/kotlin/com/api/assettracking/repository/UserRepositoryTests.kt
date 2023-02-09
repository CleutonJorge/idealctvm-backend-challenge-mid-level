package com.api.assettracking.repository

import com.api.assettracking.enums.RoleName
import com.api.assettracking.models.UserModel
import com.api.assettracking.models.security.RoleModel
import com.api.assettracking.repositories.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

class UserRepositoryTests {

    @Mock
    var repository: UserRepository? = null

    @BeforeEach
    fun initMocks() {
        MockitoAnnotations.openMocks(this)
    }

    val roleDAO = RoleModel(
        roleName = RoleName.ROLE_USER
    )

    val passwordEncrypted = BCryptPasswordEncoder().encode("123")

    val userDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa",
        id = UUID.randomUUID(),
        password = passwordEncrypted,
        type = "CPF",
        roles = listOf(roleDAO)
    )

    val daoUpdatedDAO = UserModel(
        documentNumber = 22400527083,
        fullName = "João Costa Silva",
        id = UUID.randomUUID(),
        password = passwordEncrypted,
        type = "CPF",
        roles = listOf(roleDAO)
    )

    @Test
    fun `must save user`() {

        // execution
        repository?.save(userDAO)

        //validation
        Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())

    }

    @Test
    fun `must update user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(userDAO))

        // execution
        repository?.save(daoUpdatedDAO)

        //validation
        Mockito.verify(repository, Mockito.atLeastOnce())?.save(Mockito.any())
    }

    @Test
    fun `must return user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(userDAO))

        // execution
        val result = repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633"))

        //validation
        Assertions.assertNotNull(result)
    }

    @Test
    fun `must delete user`() {

        Mockito.`when`(repository?.findById(UUID.fromString("4f35ac77-a773-41db-9814-3abe1a7e8633")))
            .thenReturn(Optional.of(userDAO))

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
