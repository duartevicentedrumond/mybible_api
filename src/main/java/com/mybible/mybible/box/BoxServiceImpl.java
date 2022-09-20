package com.mybible.mybible.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxRepository boxRepository;

    @Override
    public Box saveBox(Box box) {
        return boxRepository.save(box);
    }

    @Override
    public List<Box> getAllBox() {
        return boxRepository.findAllByOrderByBoxId();
    }

    @Override
    public Box getBox(Long boxId) {
        return boxRepository.findByBoxId(boxId);
    }

    @Override
    public Box updateBox(Long boxId, Box box) {
        return boxRepository.save(box);
    }

    @Override
    public void deleteBox(Long boxId) {
        boxRepository.deleteById(boxId);
    }
}
