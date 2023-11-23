package com.example.mainmicroservice.repository;

import com.example.mainmicroservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findTopByOrderByIdDesc();
}
