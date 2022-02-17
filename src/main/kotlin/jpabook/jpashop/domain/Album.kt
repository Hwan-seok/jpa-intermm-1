package jpabook.jpashop.domain

import javax.persistence.Entity


@Entity
class Album(
    val artist: String,
    val etc: String,
    name: String,
    price: Int,
    stockQuantity: Int,
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity,
)