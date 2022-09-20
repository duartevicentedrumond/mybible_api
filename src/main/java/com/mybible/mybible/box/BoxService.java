package com.mybible.mybible.box;

import java.util.List;

public interface BoxService {

    Box saveBox(Box box);
    Box getBox(Long boxId);
    Box updateBox(Long boxId, Box box);
    List<Box> getAllBox();
    void deleteBox(Long boxId);
}
