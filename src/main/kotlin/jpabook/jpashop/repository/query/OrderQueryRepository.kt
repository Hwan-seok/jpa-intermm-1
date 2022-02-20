package jpabook.jpashop.repository.query

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderQueryRepository(
    val em: EntityManager,
) {

    fun findOrders(): MutableList<OrderQueryDto> {
        val resultOrders = findOrderQueryDto()
        resultOrders.forEach {
            it.orderItems.addAll(findOrderItemQueryDto(it.orderId))
        }

        return resultOrders
    }

    fun findOrdersOptimized(): MutableList<OrderQueryDto> {
        val orders = findOrderQueryDto()
        val orderIds = orders.map { it.orderId }
        val orderItems = em.createQuery(
            "select new jpabook.jpashop.repository.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                    " from OrderItem oi" +
                    " join oi.item i" +
                    " where oi.order.id in :orderIds",
            OrderItemQueryDto::class.java
        )
            .setParameter("orderIds", orderIds)
            .resultList

        val orderItemMap = orderItems.groupBy { it.orderId }
        orders.forEach {
            orderItemMap[it.orderId]?.let { it1 -> it.orderItems.addAll(it1) }
        }

        return orders
    }


    private fun findOrderItemQueryDto(orderId: Long): MutableList<OrderItemQueryDto> {
        return em.createQuery(
            "select new jpabook.jpashop.repository.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                    " from OrderItem oi" +
                    " join oi.item i" +
                    " where oi.order.id = :orderId",
            OrderItemQueryDto::class.java
        )
            .setParameter("orderId", orderId)
            .resultList
    }

    private fun findOrderQueryDto(): MutableList<OrderQueryDto> {
        return em.createQuery(
            "select new jpabook.jpashop.repository.query.OrderQueryDto(o.id, m.name, o.orderDate, o.delivery.address) from Order o" +
                    " join o.member m" +
                    " join o.delivery d", OrderQueryDto::class.java
        ).resultList
    }

}
