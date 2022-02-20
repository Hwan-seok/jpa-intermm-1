package jpabook.jpashop.repository.query

import com.fasterxml.jackson.annotation.JsonIgnore

data class OrderItemQueryDto(

    @JsonIgnore
    val orderId: Long,
    val name: String,
    val orderPrice: Int,
    val count: Int,
)
