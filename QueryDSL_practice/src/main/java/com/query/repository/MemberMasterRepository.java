package com.query.repository;

import com.query.entity.member.Member;
import com.query.entity.member.*;
import com.query.repository.jpa.MemberRepository;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.query.entity.member.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MemberMasterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final MemberRepository memberRepository;

    public Member saveMember(MemberDto memberDto) {
        return memberRepository.save(Member.builder()
                .email(memberDto.getEmail())
                .money(memberDto.getMoney())
                .name(memberDto.getName()).build());
    }

    public Member findMember(Long userid) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(userid))
                .fetchOne();
    }

    public List<Member> findListMember() {
        return jpaQueryFactory
                .selectFrom(member)
                .leftJoin(member.following)
                .fetchJoin()
                .where(member.money.between(5000, 10000))
                .fetch();
    }

    public List<Member> searchMember(String str) {
        return jpaQueryFactory.query()
                .select(member)
                .from(member)
                .where(member.name.like("%" + str + "%"))
                .fetch();
    }

    public void updateMember(MemberDto memberDto) {
        UpdateClause<JPAUpdateClause> updateClause = jpaQueryFactory.update(member);

        if(memberDto.getEmail() != null) {
            updateClause.set(member.email, memberDto.getEmail());
        }

        if(memberDto.getName() != null) {
            updateClause.set(member.name, memberDto.getName());
        }

        if(memberDto.getMoney() != null) {
            updateClause.set(member.money, memberDto.getMoney());
        }

        updateClause.where(member.id.eq(memberDto.getId()))
                .execute();
    }

    public void buyProduct(Long id, Long price) {
        UpdateClause<JPAUpdateClause> updateClause = jpaQueryFactory.update(member)
                .set(member.money, member.money.add(-price));
        updateClause.where(member.id.eq(id))
                .execute();
    }

    public void followMember(Long id, Long who) {
        jpaQueryFactory.update(member)
                .set(member.following, findMember(who))
                .where(member.id.eq(id))
                .execute();
    }

    public List<MemberVO> findListMemberV1() {
        return jpaQueryFactory.query()
                .select(new QMemberVO(member.name, member.email, member.id, member.money))
                .from(member)
                .fetch();
    }

    public List<MemberVO2> findListMemberV2() {
        return jpaQueryFactory.query()
                .select(new QMemberVO2(member.name,
                        member.email, member.id, member.money,
                        subQuery(member.following)))
                .from(member)
                .fetch();
    }

    private Expression<? extends MemberSub> subQuery(QMember m) {
        return new QMemberSub(m.id, m.name, m.email, m.money);
    }
}
