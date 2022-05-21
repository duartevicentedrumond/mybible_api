package com.mybible.mybible.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByOrderByTypeId();
    Optional<Type> findByTypeId(Long typeId);
}
