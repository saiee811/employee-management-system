package com.employee.EmployeeManagement.exception;

public class EmployeeAlredayExistsException extends RuntimeException{

    public EmployeeAlredayExistsException(String msg){
        super(msg);
    }
}
