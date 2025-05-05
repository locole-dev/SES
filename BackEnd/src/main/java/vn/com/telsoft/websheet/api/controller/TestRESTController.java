package vn.com.telsoft.websheet.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.model.Employee;
import vn.com.telsoft.websheet.api.service.EmpService;

import java.util.List;

@RestController
public class TestRESTController {
    public static final Logger logger = LoggerFactory.getLogger(TestRESTController.class);

    @Autowired
    private EmpService empService;

    @RequestMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome to VNPT-SES websheet API.";
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employees
    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> lstEmps = null;
        try {
            lstEmps = empService.getEmployees();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return lstEmps;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        Employee emp = null;
        try {
            emp = empService.getEmployee(empNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return emp;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {

        logger.info("(Service Side) Creating employee: " + emp.getEmpNo());

        Employee empRes = null;
        try {
            empRes = empService.addEmployee(emp);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return empRes;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {

        logger.info("(Service Side) Editing employee: " + emp.getEmpNo());

        Employee empRes = null;
        try {
            empRes = empService.updateEmployee(emp);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return empRes;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {

        logger.info("(Service Side) Deleting employee: " + empNo);

        try {
            empService.deleteEmployee(empNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
    }

}
