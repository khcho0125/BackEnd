package com.mapstruct.mapper;

import com.mapstruct.entity.Member;
import com.mapstruct.entity.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomMapper extends GenericMapper<MemberDto, Member> {

    @Override
    @Mapping(target = "email", expression = "java(memberDto.getName() + \"@dsm.hs.kr\")")
    Member toEntity(MemberDto memberDto);
}
