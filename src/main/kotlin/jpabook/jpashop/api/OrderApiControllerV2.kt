package jpabook.jpashop.api

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/orders")
class OrderApiControllerV2(val orderRepository: OrderRepository) {


    @GetMapping("/v2")
    fun getOrdersV2(): List<OrderDto> {
        val orders = orderRepository.findAll()
        return orders.map { OrderDto(it) }
    }

    @GetMapping("/v3")
    fun getOrdersV3(): List<OrderDto> {
        val orders = orderRepository.findAllWithItem()
        return orders.map { OrderDto(it) }
    }

    @GetMapping("/v3.1")
    fun getOrdersV31(): List<OrderDto> {
        val orders = orderRepository.findAllWithMemberAndDelivery()
        return orders.map { OrderDto(it) }
    }
}


class OrderDto {

    val id: Long
    val name: String
    val orderStatus: OrderStatus
    val orderDate: LocalDateTime
    val address: Address
    val items: List<OrderItemDto>

    constructor(o: Order) {
        this.id = o.id!!
        this.name = o.member.name
        this.orderStatus = o.status
        this.orderDate = o.orderDate
        this.address = o.delivery.address
        this.items = o.orderItems.map { OrderItemDto(it) }
    }
}

class OrderItemDto {

    val count: Int
    val price: Int
//    val name: String

    constructor(i: OrderItem) {
        count = i.count
        price = i.orderPrice
//        name = i.item.name
    }
}