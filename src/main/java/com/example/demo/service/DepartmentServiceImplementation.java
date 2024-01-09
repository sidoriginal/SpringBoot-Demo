package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService{


    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepById(Long DepId) throws DepartmentNotFoundException {
        Optional<Department> department= departmentRepository.findById(DepId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Present");
        }

        return department.get();
    }

    @Override
    public void delDepById(Long depId) {
      departmentRepository.deleteById(depId);
    }

    @Override
    public Department updt(Long depId, Department department) {
        Department department1=departmentRepository.findById(depId).get();
        if(Objects.nonNull(department.getDepartmentName())&&!"".equalsIgnoreCase(department.getDepartmentName())){
            department1.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode())&&!"".equalsIgnoreCase(department.getDepartmentCode())){
            department1.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress())&&!"".equalsIgnoreCase(department.getDepartmentAddress())){
            department1.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(department1);
    }
}
