package com.example.memberserver.services;

import com.example.memberserver.dto.MemberDto;
import com.example.memberserver.dto.MemberResponseDto;
import com.example.memberserver.entities.Member;

import java.util.List;

public interface MemberService {

    Member createMember(MemberDto member, int user_id);

    Member updateMember(Long id, MemberDto memberDto,int user_id);

    void deleteMember(Long id);

    MemberResponseDto getMemberById(Long id);

    List<MemberResponseDto> getAllMembers();
}