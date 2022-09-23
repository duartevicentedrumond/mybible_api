package com.mybible.mybible.gift;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybible.mybible.box.Box;
import com.mybible.mybible.building.Building;
import com.mybible.mybible.furniture.Furniture;
import com.mybible.mybible.gifttype.Gifttype;
import com.mybible.mybible.item.Item;
import com.mybible.mybible.person.Person;
import com.mybible.mybible.room.Room;
import com.mybible.mybible.section.Section;
import com.mybible.mybible.transaction.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Gift")
public class Gift {

    @Id
    @SequenceGenerator(
            name = "gift_sequence",
            sequenceName = "gift_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "gift_sequence"
    )
    @Column(
            name = "gift_id",
            updatable = false,
            unique = true
    )
    private Long giftId;

    @Column(name = "value")
    private Double value;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "from")
    private Boolean from;

    @ManyToOne
    @JoinColumn(name = "gifttype_id")
    @JsonIgnoreProperties({"description"})
    private Gifttype gifttype;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties(
            value= {
                    "description",
                    "date",
                    "totalAmount",
                    "types",
                    "transactionParent",
                    "transactionChildren",
                    "subtransactions"
            },
            allowSetters = true
            )
    private Transaction transaction;

    @ManyToMany
    @JoinTable(
            name = "gift_people",
            joinColumns = { @JoinColumn(name = "gift_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") }
    )
    @JsonIgnoreProperties({
            "nickname",
            "fullName",
            "birthday",
            "age",
            "starred"
    })
    private Set<Person> people;

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

    @OneToOne
    @JoinColumn(name = "item_id")
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
    private Item item;

    public Gift() {
    }

    public Gift(Long giftId, Double value, Date date, String description, Boolean from, Gifttype gifttype, Transaction transaction, Set<Person> people, Building building, Room room, Furniture furniture, Section section, Box box, Item item) {
        this.giftId = giftId;
        this.value = value;
        this.date = date;
        this.description = description;
        this.from = from;
        this.gifttype = gifttype;
        this.transaction = transaction;
        this.people = people;
        this.building = building;
        this.room = room;
        this.furniture = furniture;
        this.section = section;
        this.box = box;
        this.item = item;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFrom() {
        return from;
    }

    public void setFrom(Boolean from) {
        this.from = from;
    }

    public Gifttype getGifttype() {
        return gifttype;
    }

    public void setGifttype(Gifttype gifttype) {
        this.gifttype = gifttype;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
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
        return "Gift{" +
                "giftId=" + giftId +
                ", value=" + value +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", from=" + from +
                ", gifttype=" + gifttype +
                ", transaction=" + transaction +
                ", people=" + people +
                ", building=" + building +
                ", room=" + room +
                ", furniture=" + furniture +
                ", section=" + section +
                ", box=" + box +
                ", item=" + item +
                '}';
    }
}
