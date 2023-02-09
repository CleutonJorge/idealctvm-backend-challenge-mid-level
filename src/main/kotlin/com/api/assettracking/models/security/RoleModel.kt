package com.api.assettracking.models.security

import com.api.assettracking.enums.RoleName
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import java.io.Serializable
import java.util.*


@Entity
@Table(name = "TB_ROLE")
class RoleModel (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private var roleName: RoleName? = null,

): GrantedAuthority, Serializable {

    override fun getAuthority(): String {
        return roleName.toString()
    }
    companion object {
        private const val serialVersionUID = 1L
    }
}