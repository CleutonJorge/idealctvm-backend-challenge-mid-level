package com.api.assettracking.configs.security

import com.api.assettracking.models.UserModel
import org.springframework.security.core.userdetails.User;
import com.api.assettracking.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val userModel: UserModel = userRepository.findByDocumentNumber(username.toLong())
            .orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }
        return User(
            userModel.username,
            userModel.password,
            true,
            true,
            true,
            true,
            userModel.authorities
        )
    }
}