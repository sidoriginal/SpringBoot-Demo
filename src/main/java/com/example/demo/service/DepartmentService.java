package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    List<Department> fetchDepList();

    public Department fetchDepById(Long DepId) throws DepartmentNotFoundException;

    public void delDepById(Long depId);

    public Department updt(Long depId, Department department);
}
