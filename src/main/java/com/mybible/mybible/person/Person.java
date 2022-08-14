package com.mybible.mybible.person;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Person")
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_sequence"
    )
    @Column(
            name = "person_id",
            unique = true
    )
    private Long personId;

    @Column(
            name = "nickname",
            updatable = true
    )
    private String nickname;

    @Column(
            name = "full_name",
            updatable = true,
            nullable = true
    )
    private String fullName;

    @Column(
            name = "birthday",
            nullable = true
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    public Person() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", nickname='" + nickname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

