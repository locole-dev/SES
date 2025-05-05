package vn.com.telsoft.websheet.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.dao.EmployeeDAO;
import vn.com.telsoft.websheet.api.model.Employee;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmpRESTController {
    public static final Logger logger = LoggerFactory.getLogger(EmpRESTController.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    // URL:
    // http://localhost:8080/SomeContextPath/employees
    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {

        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

        return employeeDAO.addEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {

        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

        return employeeDAO.updateEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {

        System.out.println("(Service Side) Deleting employee: " + empNo);

        employeeDAO.deleteEmployee(empNo);
    }

}
