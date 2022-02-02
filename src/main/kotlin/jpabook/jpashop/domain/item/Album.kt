package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: MutableList<Category>,
    var artist: String, var etc: String,
) : Item(
    name = name,
    price = price,
    stockQuantity = stockQuantity,
    categories = categories,
) {


}
