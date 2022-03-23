package com.mapstruct;

import com.mapstruct.entity.Member;
import com.mapstruct.entity.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Member> createMember(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.addMember(memberDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Member> update(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.test(memberDto), HttpStatus.OK);
    }
}
