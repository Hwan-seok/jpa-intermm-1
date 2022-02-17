package jpabook.jpashop.domain

import javax.persistence.*


@Entity
class Member(
    @Id @GeneratedValue
    @Column(name = "member_id")
    val id: Long? = null,

    var name: String,

    @Embedded
    val address: Address? = null,

    @OneToMany(mappedBy = "member")
    val orders: MutableList<Order> = mutableListOf(),
)
