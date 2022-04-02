package com.query.controller;

import com.query.entity.member.Member;
import com.query.entity.member.MemberDto;
import com.query.entity.member.MemberVO;
import com.query.entity.member.MemberVO2;
import com.query.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public Member setMember(@RequestBody MemberDto memberDto) {
        return memberService.saveMember(memberDto);
    }

    @GetMapping("/find")
    public Member getMember(@RequestParam Long userId) {
        return memberService.findMember(userId);
    }

    @GetMapping("/list")
    public List<Member> getList() {
        return memberService.findListMember();
    }

    @GetMapping("/search/{param}")
    public List<Member> search(@PathVariable String param) {
        return memberService.searchMember(param);
    }

    @PutMapping("/update")
    public void update(@RequestBody MemberDto memberDto) {
        memberService.updateMember(memberDto);
    }

    @PutMapping("/buy")
    public void buy(@RequestParam Long id, @RequestParam Long price) {
        memberService.buyProduct(id, price);
    }

    @PostMapping("/follow/{userId}")
    public void follow(@PathVariable Long userId, @RequestParam Long whoId) {
        memberService.followMember(userId, whoId);
    }

    @GetMapping("/list/v1")
    public List<MemberVO> getListV1() {
        return memberService.findListMemberV1();
    }

    @GetMapping("/list/v2")
    public List<MemberVO2> getListV2() {
        return memberService.findListMemberV2();
    }
}
