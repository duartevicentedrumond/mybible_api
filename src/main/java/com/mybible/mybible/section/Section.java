package com.mybible.mybible.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.box.Box;
import com.mybible.mybible.building.Building;
import com.mybible.mybible.furniture.Furniture;
import com.mybible.mybible.item.Item;
import com.mybible.mybible.room.Room;
import com.mybible.mybible.subtransaction.Subtransaction;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Section")
public class Section {

    @Id
    @SequenceGenerator(
            name = "section_sequence",
            sequenceName = "section_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "section_sequence"
    )
    @Column(
            name = "section_id",
            unique = true
    )
    private Long sectionId;

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
            mappedBy = "section"
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

    @ManyToOne
    @JoinColumn(name = "room_id")
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
    private Room room;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
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
    private Furniture furniture;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "section"
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
            mappedBy = "section"
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

    public Section() {
    }

    public Section(Long sectionId, String name, Boolean active, Date since, Date until) {
        this.sectionId = sectionId;
        this.name = name;
        this.active = active;
        this.since = since;
        this.until = until;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
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
        return "Section{" +
                "sectionId=" + sectionId +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", since=" + since +
                ", until=" + until +
                ", subtransactions=" + subtransactions +
                ", building=" + building +
                ", room=" + room +
                ", furniture=" + furniture +
                ", boxes=" + boxes +
                ", items=" + items +
                '}';
    }
}

