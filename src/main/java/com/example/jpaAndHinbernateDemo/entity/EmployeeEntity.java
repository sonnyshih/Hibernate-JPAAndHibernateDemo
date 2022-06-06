package com.example.jpaAndHinbernateDemo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Employee", schema = "hibernate_demo")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "FirstName")
    private String firstName;
    @Basic
    @Column(name = "LastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "DeptID", referencedColumnName = "ID", nullable = false)
    private DepartmentEntity departmentByDeptId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity entity = (EmployeeEntity) o;
        return id == entity.id && Objects.equals(firstName, entity.firstName) && Objects.equals(lastName, entity.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public DepartmentEntity getDepartmentByDeptId() {
        return departmentByDeptId;
    }

    public void setDepartmentByDeptId(DepartmentEntity departmentByDeptId) {
        this.departmentByDeptId = departmentByDeptId;
    }
}
