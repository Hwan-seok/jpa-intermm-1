package jpabook.jpashop

import jpabook.jpashop.domain.*
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct
import javax.persistence.EntityManager

@Component
class InitDb(val initService: InitService) {

    @PostConstruct
    fun initDb() {
        initService.dbInit();
        initService.dbInit2();
    }

}


@Component
@Transactional
class InitService(val em: EntityManager) {
    fun dbInit() {

        val member = Member(
            name = "my name",
            address = Address(
                city = "city1",
                street = "street",
                zipCode = 12345,
            )
        )

        em.persist(member)

        val book1 = Book(
            name = "book",
            author = "author",
            stockQuantity = 10,
            price = 1000,
            isbn = "1123"
        )

        val book2 = Book(
            name = "book2",
            author = "author",
            stockQuantity = 10,
            price = 1000,
            isbn = "1123"
        )

        em.persist(book1)
        em.persist(book2)

        val createOrderItem1 = OrderItem.createOrderItem(item = book1, orderPrice = 1000, count = 1)
        val createOrderItem2 = OrderItem.createOrderItem(item = book2, orderPrice = 1000, count = 1)

        val delivery = Delivery(
            address = member.address!!,
        )
        val order =
            Order.createOrder(member = member, delivery = delivery, createOrderItem1, createOrderItem2)

        em.persist(order)
    }

    fun dbInit2() {

        val member = Member(
            name = "my 222",
            address = Address(
                city = "city222",
                street = "street2222",
                zipCode = 12345,
            )
        )

        em.persist(member)

        val book1 = Book(
            name = "book222",
            author = "author222",
            stockQuantity = 10,
            price = 1000,
            isbn = "112322"
        )

        val book2 = Book(
            name = "book44442",
            author = "author",
            stockQuantity = 10,
            price = 1000,
            isbn = "1123"
        )

        em.persist(book1)
        em.persist(book2)

        val createOrderItem1 = OrderItem.createOrderItem(item = book1, orderPrice = 1000, count = 1)
        val createOrderItem2 = OrderItem.createOrderItem(item = book2, orderPrice = 1000, count = 1)

        val delivery = Delivery(
            address = member.address!!,
        )
        val order =
            Order.createOrder(member = member, delivery = delivery, createOrderItem1, createOrderItem2)

        em.persist(order)
    }
}