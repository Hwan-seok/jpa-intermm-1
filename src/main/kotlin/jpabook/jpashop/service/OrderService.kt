package jpabook.jpashop.service

import jpabook.jpashop.domain.Delivery
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val memberService: MemberService,
    private val itemService: ItemService,
    private val orderRepository: OrderRepository
) {

    fun getById(orderId: Long): Order? {
        return orderRepository.findOne(orderId)
    }

    // 주문

    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long? {
        val findMember = memberService.findOne(memberId)!!
        val item = itemService.findOne(itemId)!!

        val delivery = Delivery(address = findMember.address!!)
        val orderItem = OrderItem.createOrderItem(item = item, orderPrice = 1000, count = count)

        val createOrder = Order.createOrder(findMember, delivery, orderItem)

        orderRepository.save(createOrder)

        return createOrder.id
    }

    // 취소

    @Transactional
    fun cancelOrder(orderId: Long) {
        val findOne = orderRepository.findOne(orderId)
        findOne?.cancelOrder()
    }

    // 검색

//    fun findOrders(orderSearch:OrderSearch){
//        return orderRepository.findAll();
//    }
}