package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity


@Entity
@DiscriminatorValue("M")
class Movie(
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: MutableList<Category>,
    var director: String,
    var actor: String,
) :
    Item(name = name, price = price, stockQuantity = stockQuantity, categories = categories) {

}