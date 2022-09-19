package com.mybible.mybible.person;

import org.hibernate.annotations.ColumnDefault;

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
            name = "nickname"
    )
    private String nickname;

    @Column(
            name = "full_name"
    )
    private String fullName;

    @Column(
            name = "birthday"
    )
    private Date birthday;

    @ColumnDefault("false")
    @Column(
            name = "starred"
    )
    private Boolean starred;

    @Column(
            name = "age"
    )
    private Long age;

    public Person() {
    }

    public Person(Long personId, String nickname, String fullName, Date birthday, Boolean starred, Long age) {
        this.personId = personId;
        this.nickname = nickname;
        this.fullName = fullName;
        this.birthday = birthday;
        this.starred = starred;
        this.age = age;
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

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", nickname='" + nickname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", starred=" + starred +
                ", age=" + age +
                '}';
    }
}

