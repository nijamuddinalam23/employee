package com.employee.service;

import com.employee.dto.EmployeeDto;
import com.employee.dto.PageResponseDto;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

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

    public void deleteEmployeeById(long id) {
        Optional<Employee> opEmp = employeeRepository.findById(id);
        if (opEmp.isPresent()) {
            employeeRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException(" Employee not found with id "+id);
        }

    }

    public PageResponseDto getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> pageEmp = employeeRepository.findAll(pageable);

        List<EmployeeDto> employeeDtos = pageEmp.getContent().stream()
                .map(emp -> new EmployeeDto(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getMobile()
                ))
                .toList();

        return new PageResponseDto(
                employeeDtos,
                pageEmp.getNumber(),          // current page number
                pageEmp.getSize(),            // records per page
                pageEmp.getTotalElements(),   // total records
                pageEmp.getTotalPages(),      // total pages
                pageEmp.isFirst(),            // first page?
                pageEmp.isLast()              // last page?
        );
    }

    public void updateEmployee(long id, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmp = employeeRepository.findById(id);
        if(optionalEmp.isPresent()) {
            Employee employee = optionalEmp.get();
            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setMobile(employeeDto.getMobile());
            employeeRepository.save(employee);
        }
    }
}