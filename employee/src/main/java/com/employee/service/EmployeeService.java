package com.employee.service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class
EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto resposneEmployeeDto = new EmployeeDto();
        BeanUtils.copyProperties(savedEmployee,resposneEmployeeDto);
      return resposneEmployeeDto;
    }

}
