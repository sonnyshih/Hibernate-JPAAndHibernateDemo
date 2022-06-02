package com.example.jpaAndHinbernateDemo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Department", schema = "hibernate_demo", catalog = "")
public class DepartmentEntity {

    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "Name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
