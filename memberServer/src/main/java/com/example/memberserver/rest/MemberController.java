package com.example.memberserver.rest;

import com.example.memberserver.dto.MemberDto;
import com.example.memberserver.dto.MemberResponseDto;
import com.example.memberserver.entities.Member;
import com.example.memberserver.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // GET all members
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }



    // GET member by id
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    // CREATE member
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody MemberDto memberDto, @RequestHeader("x-user-id") int userId) {
        Member created = memberService.createMember(memberDto,userId);
        return ResponseEntity.ok(created);
    }

    // UPDATE member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id,
                                               @RequestBody MemberDto memberDto,
                                               @RequestHeader("x-user-id") int userId) {
        Member updated = memberService.updateMember(id, memberDto, userId);
        return ResponseEntity.ok(updated);
    }


    // DELETE member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}