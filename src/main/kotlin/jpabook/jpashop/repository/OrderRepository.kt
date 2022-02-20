package jpabook.jpashop.repository

import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderRepository(val em: EntityManager) {


    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(orderId: Long): Order? {
        return em.find(Order::class.java, orderId)
    }

    fun findAll(): MutableList<Order> {
        return em.createQuery("select o from Order o join o.member m ", Order::class.java).resultList
    }

    // querydsl 사용 필요
    fun findAll(orderSearch: OrderSearch): MutableList<Order>? {
        return em.createQuery("select o from Order o join o.member m ", Order::class.java).resultList

    }

    fun findAllWithMemberAndDelivery(): MutableList<Order> {
        return em.createQuery(
            "select o from Order o" +
                    " join fetch o.member" +
                    " join fetch o.delivery",
            Order::class.java
        ).resultList
    }

    fun findAllWithItem(): MutableList<Order> {
        return em.createQuery(
            "select distinct o from Order o" +
                    " join fetch o.member" +
                    " join fetch o.delivery" +
                    " join fetch o.orderItems oi" +
                    " join fetch oi.item i",
            Order::class.java
        )
            .resultList
    }
}