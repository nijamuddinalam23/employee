package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.dto.PageResponseDto;
import com.employee.dto.ResponseDto;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
//http://localhost:8080/api/v1/employee
//@RestController  this is the combination of the two @Controller + @Responsebody
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    //supply JSON(with employee data)
    @PostMapping("/save")

    public ResponseEntity<ResponseDto<EmployeeDto>> saveEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result) {
        if(result.hasErrors()){
            ResponseDto<EmployeeDto> responseDto = new ResponseDto<>();
            responseDto.setData(employeeDto);
            responseDto.setStatus(500);
            responseDto.setMessage(result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//ResponseEntity uses:ResponseEntity lets you control the whole HTTP response: the body, the status code, and the headers.
// A plain return type only lets you control the body.

        EmployeeDto dto = employeeService.saveEmployee(employeeDto);
        ResponseDto<EmployeeDto> responseDto = new ResponseDto<>();
        responseDto.setData(dto);
        responseDto.setStatus(201);
        responseDto.setMessage("Employee saved successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    //delete details

    /// /http://localhost:8080/api/v1/employee/delete/1
    @DeleteMapping("/delete/{id}")// id here same name as the param id
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee/all?pageNo=0&pageSize=4&sortBy=name&sortDir=asc
    @GetMapping("/all")
    public ResponseEntity<ResponseDto<PageResponseDto>> getEmployees(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "4") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {

        PageResponseDto pageResponseDto = employeeService.getEmployees(pageNo, pageSize, sortBy, sortDir);

        ResponseDto<PageResponseDto> responseDto = new ResponseDto<>();
        responseDto.setData(pageResponseDto);
        responseDto.setStatus(200);
        responseDto.setMessage("Successful");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/employee/update?id=4
    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(
             @RequestParam long id, @RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(id, employeeDto);
        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setData("Employee updated successfully");
        responseDto.setStatus(200);
        responseDto.setMessage("Successful");

        return new ResponseEntity(responseDto, HttpStatus.OK);

    }


}
