package com.example.flutter_gpt_project_backend.member.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flutter_gpt_project_backend.member.entity.GoogleProvider;

public interface GoogleProviderRepository extends JpaRepository<GoogleProvider, UUID> {

    // This interface can be extended with Google-specific methods if needed.
    // For now, it inherits all methods from OAuth2ProviderRepository.
}
