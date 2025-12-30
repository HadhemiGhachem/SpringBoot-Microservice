package com.example.memberserver.services;

import com.example.memberserver.dto.MemberDto;
import com.example.memberserver.dto.MemberResponseDto;
import com.example.memberserver.entities.Member;
import com.example.memberserver.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(MemberDto memberdto, int user_id) {
        Member member = new Member();
        member.setLastName(memberdto.getLastName());
        member.setFirstName(memberdto.getFirstName());
        member.setPhone(memberdto.getPhone());
        if (memberdto.getStatus() == null) {
            member.setStatus("ACTIVE");
        }
        member.setUser_id(user_id);
        member.setEmail(memberdto.getEmail());

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, MemberDto memberDto,int userId) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Member not found"));

        if (userId != existing.getUser_id()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not authorized to update this member");
        }

        existing.setFirstName(memberDto.getFirstName());
        existing.setLastName(memberDto.getLastName());
        existing.setEmail(memberDto.getEmail());
        existing.setPhone(memberDto.getPhone());
        existing.setStatus(memberDto.getStatus());

        return memberRepository.save(existing);
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }

    @Override
    public MemberResponseDto getMemberById(Long id) {
        Member member= memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setStatus(member.getStatus());
        memberResponseDto.setPhone(member.getPhone());
        memberResponseDto.setId(member.getId());
        memberResponseDto.setFirstName(member.getFirstName());
        memberResponseDto.setLastName(member.getLastName());
        return  memberResponseDto;
    }

    @Override
    public List<MemberResponseDto> getAllMembers() {
        List<Member> memberList= memberRepository.findAll();
        return memberList.stream().map((member -> new MemberResponseDto(member.getId(),member.getFirstName(),member.getLastName(),member.getEmail(),member.getPhone(),member.getStatus()))).toList();
    }
}
