package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @BeforeEach
    void setUp() {
        Department department=Department.builder()
                .departmentAddress("BNG")
                .departmentCode("ITMe098")
                .departmentName("MechTech")
                .build();
        testEntityManager.persist(department);
    }


    @Test
    public void whenfindById_thenPass(){
        Department department=departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(),"MechTech");
    }
}