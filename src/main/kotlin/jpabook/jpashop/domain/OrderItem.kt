package jpabook.jpashop.domain

import jpabook.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItem(

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null,

    @OneToMany
    var item: MutableList<Item>,

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,

    var count: Int,
)


