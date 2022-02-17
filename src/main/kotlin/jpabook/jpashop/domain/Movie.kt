package jpabook.jpashop.domain

import javax.persistence.Entity


@Entity
class Movie(
    val director: String,
    val actor: String,
    name: String,
    price: Int,
    stockQuantity: Int,
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity,
)