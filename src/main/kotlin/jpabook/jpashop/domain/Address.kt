package jpabook.jpashop.domain

import javax.persistence.Embeddable

@Embeddable
class Address(
    var zipCode: String,
    var street: String,
    var city: String,
) {

}
