package com.example.flutter_gpt_project_backend.member.repository;

import com.example.flutter_gpt_project_backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    // JpaRepository provides basic CRUD operations, no additional methods needed for now
    Optional<Member> findMemberByUserId(String userId);


}
