package com.mybible.mybible.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByOrderByTypeId();
    Type findByTypeId(Long typeId);
}
