package vn.com.telsoft.websheet.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.com.telsoft.websheet.api.model.Employee;
import vn.com.telsoft.websheet.api.service.EmpService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {

    @Value("${emp.ws.address}")
    private String wsAddress;

    static final String URL_GET_EMPLOYEES = "/employees";
    static final String URL_GET_EMPLOYEE = "/employee/{empNo}";
    static final String URL_CREATE_EMPLOYEE = "/employee";
    static final String URL_UPDATE_EMPLOYEE = "/employee";
    static final String URL_EMPLOYEE_PREFIX = "/employee";
    static final String URL_DELETE_EMPLOYEE = "/employee/{empNo}";

    @Override
    public List<Employee> getEmployees() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // Gửi yêu cầu với phương thức GET và Headers mặc định.
        Employee[] arrEmps = restTemplate.getForObject(wsAddress + URL_GET_EMPLOYEES, Employee[].class);

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(arrEmps)
                .map(object -> mapper.convertValue(object, Employee.class))
                .collect(Collectors.toList());

    }

    @Override
    public Employee getEmployee(String empNo) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        //URI variable
        Object[] uriValues = new Object[]{empNo};

        // Gửi yêu cầu với phương thức GET và Headers mặc định.
        Employee emp = restTemplate.getForObject(wsAddress + URL_GET_EMPLOYEE, Employee.class, uriValues);

        return emp;
    }

    @Override
    public Employee addEmployee(Employee emp) throws Exception {
        Employee e = null;
        RestTemplate restTemplate = new RestTemplate();

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Employee> requestBody = new HttpEntity<>(emp);

        // Gửi yêu cầu với phương thức POST.
        ResponseEntity<Employee> result
                = restTemplate.postForEntity(wsAddress + URL_CREATE_EMPLOYEE, requestBody, Employee.class);

        System.out.println("Status code:" + result.getStatusCode());

        // Code = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
            e = result.getBody();
            System.out.println("(Client Side) Employee Created: " + e.getEmpNo());
        }
        return e;
    }

    @Override
    public Employee updateEmployee(Employee emp) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Employee> requestBody = new HttpEntity<>(emp, headers);

        // Gửi yêu cầu với phương thức PUT.
        restTemplate.put(wsAddress + URL_UPDATE_EMPLOYEE, requestBody, new Object[]{});

        String resourceUrl = wsAddress + URL_EMPLOYEE_PREFIX + "/" + emp.getEmpNo();

        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);

        if (e != null) {
            System.out.println("(Client side) Employee after update: ");
            System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
        }
        return e;
    }

    @Override
    public void deleteEmployee(String empNo) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        //URI variable
        Object[] uriValues = new Object[]{empNo};

        // Gửi yêu cầu với phương thức DELETE.
        restTemplate.delete(wsAddress + URL_DELETE_EMPLOYEE, uriValues);

        Employee e = restTemplate.getForObject(wsAddress + URL_DELETE_EMPLOYEE, Employee.class);

        if (e != null) {
            System.out.println("(Client side) Employee after delete: ");
            System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
        } else {
            System.out.println("Employee not found!");
        }
    }
}
