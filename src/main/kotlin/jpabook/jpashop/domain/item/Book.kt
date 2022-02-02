package jpabook.jpashop.domain.item

import jpabook.jpashop.domain.Category
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String, price: Int, stockQuantity: Int,

    categories: MutableList<Category>,
    var isbn: String, var author: String,
) :
    Item(name = name, price = price, stockQuantity = stockQuantity, categories = categories) {

}