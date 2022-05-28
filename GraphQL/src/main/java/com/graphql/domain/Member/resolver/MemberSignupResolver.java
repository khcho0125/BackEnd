package com.graphql.domain.Member.resolver;

import com.graphql.domain.Member.domain.Member;
import com.graphql.domain.Member.domain.repository.MemberRepository;
import com.graphql.domain.Member.dto.MemberSignupRequest;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional(rollbackFor = Exception.class)
public class MemberSignupResolver implements GraphQLMutationResolver {

    private final MemberRepository memberRepository;

    public Member signup(final MemberSignupRequest memberDto) {
        return memberRepository.save(Member.builder()
                .memberId(memberDto.getMemberId())
                .password(memberDto.getPassword()).build());
    }
}
