package com.springboot.member.controller.controller_v2;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.entity.entity_v2.Member;
import com.springboot.member.service.service_v2.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


/**
 * DI 적용하지 않음. 직접 객체 생성
 */
@RestController
@RequestMapping("/v2/members")
@Validated
public class MemberControllerV2 {
    private final MemberService memberService;

    public MemberControllerV2() {
        this.memberService = new MemberService();
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member = new Member();
        member.setEmail(memberDto.getEmail());
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member member = new Member();
        member.setMemberId(memberPatchDto.getMemberId());
        member.setName(memberPatchDto.getName());
        member.setPhone(memberPatchDto.getPhone());

        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> response = memberService.findMembers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
