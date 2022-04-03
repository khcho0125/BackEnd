package com.query.Service;

import com.query.entity.member.Member;
import com.query.entity.member.MemberDto;
import com.query.entity.member.MemberVO;
import com.query.entity.member.MemberVO2;
import com.query.repository.MemberMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMasterRepository repository;

    public Member create(MemberDto memberDto) {
        return repository.saveMember(memberDto);
    }

    public List<Member> searchMember(String str) {
        return repository.searchMember(str);
    }

    public Member findMember(Long userId) {
        return repository.findMember(userId);
    }

    public void updateMember(MemberDto memberDto) {
        repository.updateMember(memberDto);
    }

    public List<Member> showMemberList() {
        return repository.findListMember();
    }

    public void spendMoney(Long id, Long price) {
        repository.buyProduct(id, price);
    }

    public void following(Long userId, Long whoId) {
        repository.followMember(userId, whoId);
    }

    public List<MemberVO> showMemberListV1() {
        return repository.findListMemberV1();
    }

    public List<MemberVO2> showMemberListV2() {
        return repository.findListMemberV2();
    }
}
