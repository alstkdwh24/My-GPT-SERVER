package com.example.flutter_gpt_project_backend.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flutter_gpt_project_backend.member.entity.GoogleMember;
import com.example.flutter_gpt_project_backend.member.entity.Member;


public interface MemberRepository extends JpaRepository<Member, java.util.UUID> {
    // JpaRepository provides basic CRUD operations, no additional methods needed for now
    Optional<Member> findMemberByUserId(String userId);
    Optional<GoogleMember> findByLoginId(String loginId);

}
