package com.example.flutter_gpt_project_backend.gpt.repository;

import com.example.flutter_gpt_project_backend.gpt.entity.GptRoomEntity;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


public interface GptRoomRepository  extends JpaRepository<GptRoomEntity, UUID> {


}
