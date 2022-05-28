package com.graphql.domain.Member.resolver;

import com.graphql.domain.Member.domain.Member;
import com.graphql.domain.Member.domain.repository.MemberRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class MemberViewResolver implements GraphQLQueryResolver {

    private final MemberRepository memberRepository;

    public Member searchMember(final Long memberSn) {
        return memberRepository.findById(memberSn).orElseThrow();
    }
}
