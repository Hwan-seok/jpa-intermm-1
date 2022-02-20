package jpabook.jpashop.api

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping("/simple-orders")
class OrderApiController(val orderRepository: OrderRepository) {


    @GetMapping("/v1/sample-orders")
    fun getAllOrders(): MutableList<Order>? {
        return orderRepository.findAll()
    }

    @GetMapping("/v2")
    fun getOrdersV2(): List<GetOrdersV2Dto> {
        val orders = orderRepository.findAll()
        return orders.map {
            GetOrdersV2Dto(
                it.id!!,
                it.member.name,
                it.status,
                it.orderDate,
                it.member.address!!,
            )
        }
    }

    @GetMapping("/v3")
    fun getOrdersV3(): List<GetOrdersV2Dto> {
        val orders = orderRepository.findAllWithMemberAndDelivery()
        return orders.map {
            GetOrdersV2Dto(
                it.id!!,
                it.member.name,
                it.status,
                it.orderDate,
                it.member.address!!,
            )
        }
    }


}

data class GetOrdersV2Dto(
    val id: Long,
    val name: String,
    val orderStatus: OrderStatus,
    val orderDate: LocalDateTime,
    val address: Address,

    )