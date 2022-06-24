package ch.demo.service;

import ch.demo.entity.Employee;
import ch.demo.model.EmployeeDTO;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface EmployeeService {
    Uni<Employee> save(final EmployeeDTO employeeDTO);
    Uni<Employee> get(Long id);
    Uni<List<Employee>> getAll();
}
