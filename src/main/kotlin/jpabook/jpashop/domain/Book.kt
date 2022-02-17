package jpabook.jpashop.domain

import javax.persistence.Entity

@Entity
class Book(
    val author: String,
    val isbn: String,
    name: String,
    price: Int,
    stockQuantity: Int,
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity,
)