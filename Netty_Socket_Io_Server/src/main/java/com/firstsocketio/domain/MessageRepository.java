package com.firstsocketio.domain;

import com.firstsocketio.domain.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(nativeQuery = true, value = "select * from chat_message m where m.room_code = :code order by m.create_at desc limit 25")
    List<ChatMessage> findChatMessages(@Param("code") String roomCode);

    void deleteByRoomCode(String roomCode);
}
