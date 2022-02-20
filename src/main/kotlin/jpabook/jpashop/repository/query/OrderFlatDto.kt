package jpabook.jpashop.repository.query

import jpabook.jpashop.domain.Address
import java.time.LocalDateTime

data class OrderFlatDto(
    val orderId: Long,
    val name: String,
    val orderDate: LocalDateTime,
    val address: Address,

    val itemName: String,
    val orderPrice: Int,
    val count: Int,
)