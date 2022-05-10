package com.firstsocketio.domain;

import com.firstsocketio.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<ChatRoom, String> {

    void deleteByRoomCode(String roomName);

    boolean existsByRoomName(String roomName);
}
