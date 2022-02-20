package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order private constructor(
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    val delivery: Delivery,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderItems: MutableList<OrderItem> = mutableListOf(),

    val orderDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER,
) {
    companion object {
        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            val order = Order(
                member = member,
                delivery = delivery,
            )
            delivery.order = order
            order.orderItems.addAll(orderItems)
            orderItems.forEach { it.order = order }
            return order
        }
    }

    fun cancelOrder() {
        if (delivery.status == DeliveryStatus.END) throw  IllegalStateException("배송 끝나면 불가")

        status = OrderStatus.CANCEL
        orderItems.forEach { it.cancel() }
    }

    fun getTotalPrice(): Int {
        return orderItems.sumOf { it.getTotalPrice() }
    }
}
