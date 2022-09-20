package com.mybible.mybible.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.box.Box;
import com.mybible.mybible.building.Building;
import com.mybible.mybible.furniture.Furniture;
import com.mybible.mybible.item.Item;
import com.mybible.mybible.section.Section;
import com.mybible.mybible.subtransaction.Subtransaction;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Room")
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "room_sequence"
    )
    @Column(
            name = "room_id",
            unique = true
    )
    private Long roomId;

    @Column(name = "name")
    private String name;

    @ColumnDefault("true")
    @Column(name = "active")
    private Boolean active;

    @Column(name = "since")
    private Date since;

    @Column(name = "until")
    private Date until;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room"
    )
    @JsonIgnoreProperties({
            "amount",
            "category",
            "person",
            "building",
            "room",
            "furniture",
            "section",
            "box",
            "item"
    })
    private Set<Subtransaction> subtransactions;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "rooms",
            "furnitures",
            "sections",
            "boxes",
            "items"
    })
    private Building building;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room"
    )
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "building",
            "room",
            "sections",
            "boxes",
            "items"
    })
    private Set<Furniture> furnitures;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room"
    )
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "building",
            "room",
            "furniture",
            "boxes",
            "items"
    })
    private Set<Section> sections;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room"
    )
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "building",
            "room",
            "furniture",
            "section",
            "items"
    })
    private Set<Box> boxes;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "room"
    )
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "building",
            "room",
            "furniture",
            "section",
            "box"
    })
    private Set<Item> items;

    public Room() {
    }

    public Room(Long roomId, String name, Boolean active, Date since, Date until) {
        this.roomId = roomId;
        this.name = name;
        this.active = active;
        this.since = since;
        this.until = until;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public Set<Subtransaction> getSubtransactions() {
        return subtransactions;
    }

    public void setSubtransactions(Set<Subtransaction> subtransactions) {
        this.subtransactions = subtransactions;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<Furniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(Set<Furniture> furnitures) {
        this.furnitures = furnitures;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", since=" + since +
                ", until=" + until +
                ", subtransactions=" + subtransactions +
                ", building=" + building +
                ", furnitures=" + furnitures +
                ", sections=" + sections +
                ", boxes=" + boxes +
                ", items=" + items +
                '}';
    }
}

