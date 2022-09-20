package com.mybible.mybible.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public Room add(@RequestBody Room room){
        return roomService.saveRoom(room);

    }

    @GetMapping("/getAll")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    @RequestMapping("/{roomId}")
    public Room getRoom(@PathVariable Long roomId){
        return roomService.getRoom(roomId);
    }

    @PutMapping("/update/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room room){
        return roomService.updateRoom(roomId, room);
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoom(@PathVariable Long roomId){
        roomService.deleteRoom(roomId);
    }

}