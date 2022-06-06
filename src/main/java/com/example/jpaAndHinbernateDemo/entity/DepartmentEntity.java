package com.example.jpaAndHinbernateDemo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Department", schema = "hibernate_demo")
public class DepartmentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "departmentByDeptId")
    private Collection<EmployeeEntity> employeesById;

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

    public Collection<EmployeeEntity> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeEntity> employeesById) {
        this.employeesById = employeesById;
    }
}
