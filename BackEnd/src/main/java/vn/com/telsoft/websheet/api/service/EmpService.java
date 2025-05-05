package vn.com.telsoft.websheet.api.service;

import vn.com.telsoft.websheet.api.model.Employee;

import java.util.List;

public interface EmpService {

    List<Employee> getEmployees() throws Exception;

    Employee getEmployee(String empNo) throws Exception;

    Employee addEmployee(Employee emp) throws Exception;

    Employee updateEmployee(Employee emp) throws Exception;

    void deleteEmployee(String empNo) throws Exception;
}
