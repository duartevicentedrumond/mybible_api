package com.mybible.mybible.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(
            value = "SELECT " +
                        "person.* " +
                    "FROM  \"person\" " +
                    "ORDER BY starred DESC, nickname ASC",
            nativeQuery = true
    )
    List<Person> findAllByOrderByPersonId();
    Person findByPersonId(Long personId);
}
