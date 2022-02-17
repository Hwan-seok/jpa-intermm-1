package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class OrderItem {

    protected constructor(
        item: Item,
        orderPrice: Int,
        count: Int,
    ) {
        this.item = item
        this.orderPrice = orderPrice
        this.count = count
    }


    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item

    val orderPrice: Int

    val count: Int

    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(
                item = item,
                orderPrice = orderPrice,
                count = count
            )
            item.removeStock(count)
            return orderItem
        }
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    lateinit var order: Order

    fun cancel() {
        item.addStock(count)
    }

    fun getTotalPrice(): Int {
        return orderPrice * count
    }

}