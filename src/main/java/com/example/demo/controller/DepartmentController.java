package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DepartmentServiceImplementation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("Inside Save");
        return departmentService.saveDepartment(department);
    }


    @GetMapping("/departments")
    public List<Department> fetchDepList(){
        LOGGER.info("Inside Fetch");
        return departmentService.fetchDepList();
    }
    @GetMapping("/departments/{id}")
    public Department fetchDepByID(@PathVariable("id") Long DepId) throws DepartmentNotFoundException {
        return departmentService.fetchDepById(DepId);
    }

    @DeleteMapping("/departments/{id}")
    public String delDepByID(@PathVariable("id") Long DepId){
        departmentService.delDepById(DepId);
        return "Udaa Diya";
    }

    @PutMapping("/departments/{id}")
    public Department updt(@PathVariable("id") Long DepId,@RequestBody Department department){
                return departmentService.updt(DepId,department);
    }

}
