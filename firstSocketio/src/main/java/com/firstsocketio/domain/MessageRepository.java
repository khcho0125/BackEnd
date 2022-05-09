package com.firstsocketio.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(nativeQuery = true, value = "select * from chat_message m order by m.create_at desc limit 25")
    List<ChatMessage> findChatMessages();
}
