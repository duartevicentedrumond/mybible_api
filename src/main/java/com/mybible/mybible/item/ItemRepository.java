package com.mybible.mybible.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByItemId();
    Item findByItemId(Long itemId);

    @Query(
            value = "SELECT \n" +
                        "building_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'building' AS type\n" +
                    "FROM\n" +
                        "building\n" +
                    "UNION\n" +
                    "SELECT \n" +
                        "room_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'room' AS type\n" +
                    "FROM\n" +
                        "room\n" +
                    "UNION\n" +
                    "SELECT \n" +
                        "furniture_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'furniture' AS type\n" +
                    "FROM\n" +
                        "furniture\n" +
                    "UNION\n" +
                    "SELECT \n" +
                        "section_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'section' AS type\n" +
                    "FROM\n" +
                        "section\n" +
                    "UNION\n" +
                    "SELECT \n" +
                        "box_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'box' AS type\n" +
                    "FROM\n" +
                        "box\n" +
                    "UNION\n" +
                    "SELECT \n" +
                        "item_id AS id, \n" +
                        "name,\n" +
                        "active,\n" +
                        "since,\n" +
                        "until,\n" +
                        "'item' AS type\n" +
                    "FROM\n" +
                        "item\n",
            nativeQuery = true
    )
    List<Object[]> findAllJoinedByOrderByname();
}
