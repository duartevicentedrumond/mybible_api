package com.mybible.mybible.room;

import java.util.List;

public interface RoomService {

    Room saveRoom(Room room);
    Room getRoom(Long roomId);
    Room updateRoom(Long roomId, Room room);
    List<Room> getAllRoom();
    void deleteRoom(Long roomId);
}
