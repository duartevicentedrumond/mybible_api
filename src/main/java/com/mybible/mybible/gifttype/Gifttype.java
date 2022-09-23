package com.mybible.mybible.gifttype;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Gifttype")
public class Gifttype {

    @Id
    @SequenceGenerator(
            name = "gifttype_sequence",
            sequenceName = "gifttype_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "gifttype_sequence"
    )
    @Column(
            name = "gifttype_id",
            unique = true
    )
    private Long gifttypeId;

    @Column(
            name = "description"
    )
    private String description;

    public Gifttype() {
    }

    public Gifttype(Long gifttypeId, String description) {
        this.gifttypeId = gifttypeId;
        this.description = description;
    }

    public Long getGifttypeId() {
        return gifttypeId;
    }

    public void setGifttypeId(Long gifttypeId) {
        this.gifttypeId = gifttypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Gifttype{" +
                "gifttypeId=" + gifttypeId +
                ", description='" + description + '\'' +
                '}';
    }
}

