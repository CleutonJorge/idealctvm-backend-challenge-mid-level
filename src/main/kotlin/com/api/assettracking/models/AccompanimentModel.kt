package com.api.assettracking.models

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "TB_ACCOMPANIMENT")
class AccompanimentModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID? = null,

    @Column(nullable = false, length = 70)
    private val name: String? = null,

    @Column(nullable = false)
    private val createAt: LocalDateTime? = null,

    @Column(nullable = true)
    private val updateAt: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserModel? = null,

    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = [JoinColumn(name = "accompaniment_id")],
            inverseJoinColumns = [JoinColumn(name = "asset_id")]
    )
    open var assets: Set<AssetModel> = HashSet()
)