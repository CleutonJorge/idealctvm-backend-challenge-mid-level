package com.api.assettracking.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "TB_ACCOMPANIMENT")
class AccompanimentModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false, length = 70)
    private val name: String? = null,

    @Column(nullable = false)
    private val createAt: LocalDateTime? = null,

    @Column(nullable = true)
    private val updateAt: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserModel? = null,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_asset_accompaniment",
            joinColumns = [JoinColumn(name = "accompaniment_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "asset_id", referencedColumnName = "id")]
    )
    @JsonIgnoreProperties("accompaniments")
    var assets: List<AssetModel> = mutableListOf()
)