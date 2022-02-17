package jpabook.jpashop.api

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.service.MemberService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/api")
class MemberApiController(
    val memberService: MemberService,
) {

    @GetMapping("/v1/members")
    fun findMembersV1(): List<Member> {
        return memberService.findMembers()
    }

    @GetMapping("/v2/members")
    fun findMembersV2(): Result<List<MemberResponseDto>> {
        val findMembers = memberService.findMembers()
        return Result(findMembers.map { MemberResponseDto(it.name) })
    }

    data class Result<T>(
        val data: T,
    )

    data class MemberResponseDto(
        val name: String,
    )

    @PostMapping("/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        val join = memberService.join(member)
        return CreateMemberResponse(join)
    }

    @PostMapping("/v2/members")
    fun saveMemberV2(@RequestBody createMemberDto: CreateMemberDto): CreateMemberResponse {
        val (name, city, street, zipCode) = createMemberDto
        val member = Member(
            name = name,
            address = Address(
                city = city,
                street = street,
                zipCode = zipCode
            )
        )
        memberService.join(member)
        return CreateMemberResponse(member.id!!)
    }

    @PutMapping("/v2/members/{memberId}")
    fun updateMemberV2(
        @PathVariable memberId: Long,
        @RequestBody updateMemberDto: UpdateMemberDto
    ): UpdateMemberResponse {
        memberService.update(memberId, updateMemberDto)
        return UpdateMemberResponse(
            memberId,
            updateMemberDto.name
        )
    }

    data class CreateMemberDto(
        @field:NotEmpty
        val name: String,

        var city: String,

        var street: String,

        var zipCode: Int
    )

    class CreateMemberResponse(
        var id: Long,
    )

    data class UpdateMemberDto(
        val name: String,
    )

    data class UpdateMemberResponse(
        val id: Long,
        val name: String,
    )

}