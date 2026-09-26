package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.dto.ResponseDto;
import com.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//http://localhost:8080/api/v1/employee
//@RestController  this is the combination of the two @Controller + @Responsebody
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    //supply JSON(with employee data)
    @PostMapping ("/save")
    public ResponseEntity<ResponseDto<EmployeeDto>> saveEmployee(@RequestBody EmployeeDto employeeDto) {

       EmployeeDto dto= employeeService.saveEmployee(employeeDto);
       ResponseDto<EmployeeDto> responseDto = new ResponseDto<>();
        responseDto.setData(dto);
       responseDto.setStatus(201);
       responseDto.setMessage(employeeDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


}
