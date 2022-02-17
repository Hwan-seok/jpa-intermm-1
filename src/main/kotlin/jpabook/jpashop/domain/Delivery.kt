package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Delivery(
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long? = null,


    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus = DeliveryStatus.INIT,

    @Embedded
    val address: Address,
) {

    @OneToOne(mappedBy = "delivery")
    lateinit var order: Order
}
