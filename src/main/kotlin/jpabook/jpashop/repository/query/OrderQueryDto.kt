package jpabook.jpashop.repository.query

import jpabook.jpashop.domain.Address
import java.time.LocalDateTime

class OrderQueryDto(
    val orderId: Long,
    val name: String,
    val orderDate: LocalDateTime,
    val address: Address,
) {

    var orderItems = mutableListOf<OrderItemQueryDto>()
}
