package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;


    @BeforeEach
    void setUp() {
        department=Department.builder()
                .departmentAddress("BNG")
                .departmentCode("ITMe098")
                .departmentName("MechTech")
                .departmentId(3L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment=Department.builder()
                .departmentAddress("BNG")
                .departmentCode("ITMe098")
                .departmentName("MechTech")
                .departmentId(3L)
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"MechTech\",\n" +
                        "    \"departmentCode\":\"ITMe098\",\n" +
                        "    \"departmentAddress\":\"BNG\"\n" +
                        "}")
        ).andExpect(status().isOk());
    }

    @Test
    void fetchDepByID() throws Exception {
        Mockito.when(departmentService.
                fetchDepById(3L)).thenReturn(department);
        mockMvc.perform(get("/departments/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}