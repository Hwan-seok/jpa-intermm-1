package jpabook.jpashop.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column
    val city: String,
    val street: String,
    @Column(nullable = true)
    val zipCode: Int,
)