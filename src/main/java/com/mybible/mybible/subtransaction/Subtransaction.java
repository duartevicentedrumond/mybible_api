package com.mybible.mybible.subtransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.box.Box;
import com.mybible.mybible.building.Building;
import com.mybible.mybible.category.Category;
import com.mybible.mybible.furniture.Furniture;
import com.mybible.mybible.item.Item;
import com.mybible.mybible.person.Person;
import com.mybible.mybible.room.Room;
import com.mybible.mybible.section.Section;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Subtransaction")
public class Subtransaction {

    @Id
    @SequenceGenerator(
            name = "subtransaction_sequence",
            sequenceName = "subtransaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "subtransaction_sequence"
    )
    @Column(
            name = "subtransaction_id",
            updatable = false,
            unique = true
    )
    private Long subtransactionId;

    @Column(
            name = "amount",
            nullable = false
    )
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"description"})
    private Category category;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({
            "nickname",
            "fullName",
            "birthday",
            "age",
            "starred"
    })
    private Person person;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonIgnoreProperties(
            value = {
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
            },
            allowSetters = true)
    private Building building;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties(value = {
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
    },
            allowSetters = true)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
    @JsonIgnoreProperties(value = {
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
    },
            allowSetters = true)
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties(
            value = {
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
            },
            allowSetters = true)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "box_id")
    @JsonIgnoreProperties(
            value = {
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
            },
            allowSetters = true
    )
    private Box box;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties(
            value = {
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
            },
            allowSetters = true
    )
    private Item item;

    public Subtransaction() {
    }

    public Subtransaction(Long subtransactionId, Double amount, Category category, Person person, Building building, Room room, Furniture furniture, Section section, Box box, Item item) {
        this.subtransactionId = subtransactionId;
        this.amount = amount;
        this.category = category;
        this.person = person;
        this.building = building;
        this.room = room;
        this.furniture = furniture;
        this.section = section;
        this.box = box;
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getSubtransactionId() {
        return subtransactionId;
    }

    public void setSubtransactionId(Long subtransactionId) {
        this.subtransactionId = subtransactionId;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Subtransaction{" +
                "subtransactionId=" + subtransactionId +
                ", amount=" + amount +
                ", category=" + category +
                ", person=" + person +
                ", building=" + building +
                ", room=" + room +
                ", furniture=" + furniture +
                ", section=" + section +
                ", box=" + box +
                ", item=" + item +
                '}';
    }
}
