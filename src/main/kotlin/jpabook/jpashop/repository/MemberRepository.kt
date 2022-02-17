package jpabook.jpashop.repository

import jpabook.jpashop.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class MemberRepository @Autowired constructor(
//    @PersistenceContext
    val em: EntityManager,
) {

    fun save(member: Member) = em.persist(member)


    fun findById(memberId: Long): Member? {
        return em.find(Member::class.java, memberId)
    }


    fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }

    fun findByName(name: String): MutableList<Member> {
        return em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }


}