package com.mybible.mybible.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAllByOrderByRoomId();
    }

    @Override
    public Room getRoom(Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Override
    public Room updateRoom(Long roomId, Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}
