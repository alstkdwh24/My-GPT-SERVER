package com.example.flutter_gpt_project_backend.member.repository;

import com.example.flutter_gpt_project_backend.member.entity.GoogleMember;
import com.example.flutter_gpt_project_backend.member.entity.GoogleProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoogleProviderRepository extends JpaRepository<GoogleProvider, UUID> {

    // This interface can be extended with Google-specific methods if needed.
    // For now, it inherits all methods from OAuth2ProviderRepository.
}
