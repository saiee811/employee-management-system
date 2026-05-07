package com.employee.EmployeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException employeeNotFoundException){
        return new ResponseEntity<>(employeeNotFoundException.getMessage(), HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex){
        return new ResponseEntity<>("Something went wrong !!",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(EmployeeAlredayExistsException.class)
    public ResponseEntity<String> handleEmployeeAlreadyExists(EmployeeAlredayExistsException employeeAlredayExistsException){
        return new ResponseEntity<>(employeeAlredayExistsException.getMessage(), HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));

    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFoundException){
        return new ResponseEntity<>(departmentNotFoundException.getMessage(),HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<String> handleManagerNotFoundException(ManagerNotFoundException managerNotFoundException){
        return new ResponseEntity<>(managerNotFoundException.getMessage(),HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }


    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<String> handleDepartmentAlreadyExists(DepartmentAlreadyExistsException departmentAlreadyExistsException){
        return new ResponseEntity<>(departmentAlreadyExistsException.getMessage(),HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }
}
