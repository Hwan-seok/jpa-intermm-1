package jpabook.jpashop.service

import jpabook.jpashop.api.MemberApiController
import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService @Autowired constructor(
    val memberRepository: MemberRepository,
) {

    /**
     * 회원 가입
     */
    @Transactional
    fun join(member: Member): Long {
        validateDuplicateMember(member)

        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicateMember(member: Member) {
        val findByName = memberRepository.findByName(member.name)
        if (findByName.isNotEmpty()) throw IllegalStateException()
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(id: Long): Member? {
        return memberRepository.findById(id)
    }

    @Transactional
    fun update(memberId: Long, updateMemberDto: MemberApiController.UpdateMemberDto) {
        val member = memberRepository.findById(memberId)!!
        member.name = updateMemberDto.name
    }
}