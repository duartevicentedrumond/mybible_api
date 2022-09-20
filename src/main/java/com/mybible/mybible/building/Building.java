package com.mybible.mybible.building;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.box.Box;
import com.mybible.mybible.furniture.Furniture;
import com.mybible.mybible.item.Item;
import com.mybible.mybible.room.Room;
import com.mybible.mybible.section.Section;
import com.mybible.mybible.subtransaction.Subtransaction;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Building")
public class Building {

    @Id
    @SequenceGenerator(
            name = "building_sequence",
            sequenceName = "building_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "building_sequence"
    )
    @Column(
            name = "building_id",
            unique = true
    )
    private Long buildingId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @ColumnDefault("true")
    @Column(name = "active")
    private Boolean active;

    @Column(name = "since")
    private Date since;

    @Column(name = "until")
    private Date until;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "building"
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

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "building"
    )
    @JsonIgnoreProperties({
            "name",
            "location",
            "active",
            "since",
            "until",
            "subtransactions",
            "building",
            "furnitures",
            "sections",
            "boxes",
            "items"
    })
    private Set<Room> rooms;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "building"
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

    public Building() {
    }

    public Building(Long buildingId, String name, String location, Boolean active, Date since, Date until) {
        this.buildingId = buildingId;
        this.name = name;
        this.location = location;
        this.active = active;
        this.since = since;
        this.until = until;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
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
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", active=" + active +
                ", since=" + since +
                ", until=" + until +
                ", subtransactions=" + subtransactions +
                ", rooms=" + rooms +
                ", furnitures=" + furnitures +
                ", sections=" + sections +
                ", boxes=" + boxes +
                ", items=" + items +
                '}';
    }
}

