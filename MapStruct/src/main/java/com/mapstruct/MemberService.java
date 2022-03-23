package com.mapstruct;

import com.mapstruct.entity.Member;
import com.mapstruct.entity.MemberDto;
import com.mapstruct.mapper.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final CustomMapper customMapper;

    private Member member;

    public Member addMember(MemberDto memberDto) {
        this.member = customMapper.toEntity(memberDto);
        return member;
    }

    public Member test(MemberDto memberDto) {
        return customMapper.updateFromDto(memberDto, member);
    }
}
