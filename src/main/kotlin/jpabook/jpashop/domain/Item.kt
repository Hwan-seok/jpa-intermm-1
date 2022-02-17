package jpabook.jpashop.domain

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Item(
    @Id @GeneratedValue
    @Column(name = "item_id")
    val id: Long? = null,

    val name: String,
    val price: Int,
    var stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf(),

    ) {

    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val willChangedStock = this.stockQuantity - quantity
        if (willChangedStock < 0) throw IllegalStateException()
        stockQuantity = willChangedStock
    }
}
