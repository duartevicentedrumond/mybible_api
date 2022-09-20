package com.mybible.mybible.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findAllByOrderByBoxId();
    Box findByBoxId(Long boxId);
}
