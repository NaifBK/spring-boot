package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("E-mail Taken");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with ID " + employeeId + " does not exists");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Integer employeeId, String name, String email) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new IllegalStateException("Employee with ID " + employeeId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name)) {
            employee.setName(name);
        }

        if (email != null &&
                email.length() > 0 && !Objects.equals(employee.getEmail(), email)) {
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
            if (employeeOptional.isPresent()) {
                throw new IllegalStateException("E-mail Taken");
            }
            employee.setEmail(email);
        }
    }
}
