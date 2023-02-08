package com.api.assettracking.models

import com.api.assettracking.models.security.RoleModel
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "TB_USER")
class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false, length = 70)
    val fullName: String,

    @Column(nullable = false, unique = true, length = 14)
    val documentNumber: Long,

    @Column(nullable = false, length = 10)
    val type: String? = null,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private var accompaniment: List<AccompanimentModel> = mutableListOf(),

    @Column(nullable = false)
    private var password: String,

    @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "TB_USERS_ROLES",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    private val roles: List<RoleModel>

) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return documentNumber.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}