package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
internal class MemberServiceTest @Autowired constructor(
    val memberService: MemberService,
    val memberRepository: MemberRepository,
    val entityManager: EntityManager,
) {
    @Test
    fun `회원가입`() {
        val address = Address(
            city = "강남구",
            street = "강남대로", zipCode = 12345,
        )

        val member = Member(
            name = "회원1",
            address = address
        )

        val joinedMemberId = memberService.join(member)
        entityManager.flush()
        Assertions.assertThat(joinedMemberId).isEqualTo(memberRepository.findById(joinedMemberId)!!.id)
    }

    @Test
    fun `회원가입 중복회원 실패`() {
        val address = Address(
            city = "강남구",
            street = "강남대로", zipCode = 12345,
        )
        val member1 = Member(
            name = "회원1",
            address = address
        )
        val member2 = Member(
            name = "회원1",
            address = address
        )
        memberService.join(member1)

        assertThrows<IllegalStateException> {
            memberService.join(member2)
        }

    }
}