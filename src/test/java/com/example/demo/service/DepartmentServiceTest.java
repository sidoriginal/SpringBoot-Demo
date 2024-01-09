package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {
        Department department=Department.builder()
                .departmentAddress("BNG")
                .departmentId(1L)
                .departmentCode("IT098")
                .departmentName("Info Tech")
                .build();
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(Optional.ofNullable(department).get()));
    }

    @Test
    @DisplayName("Hello")
    public void whenValidDepartmentId_theDepartmentShouldFound() throws DepartmentNotFoundException {
            Long departmentId= 1L;
        Department found=departmentService.fetchDepById(departmentId);
        assertEquals(departmentId, found.getDepartmentId());
    }
}