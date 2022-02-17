package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Book
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class OrderServiceTest @Autowired constructor(
    val orderService: OrderService,
    val memberService: MemberService,
    val itemService: ItemService,
) {

    @Test
    fun `상품 주문 성공`() {
        val (member, book) = create()
        val orderId = orderService.order(memberId = member.id!!, itemId = book.id!!, 1)

        val order = orderService.getById(orderId = orderId!!)!!
        assertEquals("상품 주문 상태는 ORDER", OrderStatus.ORDER, order.status)
        assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, order.orderItems.size)
        assertEquals("주문 가격은 가격*수량이다", 1000, order.getTotalPrice())
        assertEquals("주문 수량 만큼 재고가 줄어야 한다", 2, book.stockQuantity)
    }

    private fun create(): Pair<Member, Book> {
        val address = Address(
            city = "city",
            street = "street",
            zipCode = 1235
        )
        val member = Member(
            name = "member",
            address = address,
        )
        memberService.join(member)

        val book = Book(
            name = "book",
            stockQuantity = 3,
            price = 1000,
            author = "100",
            isbn = "asdpofk"
        )
        itemService.saveItem(book)
        return Pair(member, book)
    }


    @Test
    fun `상품 주문 실패  (재고 수량 초과)`() {
        val (member, book) = create()
        assertThrows<IllegalStateException> {
            orderService.order(memberId = member.id!!, itemId = book.id!!, 10)
        }
    }

    @Test
    fun `주문 취소`() {
        val (member, book) = create()
        val orderId = orderService.order(memberId = member.id!!, itemId = book.id!!, 1)

        assertEquals("주문 취소 전엔 stock이 줄어들어야 한다", 2, book.stockQuantity)
        orderService.cancelOrder(orderId!!)
        val order = orderService.getById(orderId!!)

        assertEquals("주문 취소하면 상태가 cancel 이여야 한다", OrderStatus.CANCEL, order!!.status)
        assertEquals("주문 취소하면 다시 stock이 돌아와야한다", 3, book.stockQuantity)

    }

}