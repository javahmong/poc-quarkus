package ch.demo.service;

import ch.demo.entity.Company;
import ch.demo.entity.Employee;
import ch.demo.model.EmployeeDTO;
import ch.demo.proxy.CompanyProxy;
import ch.demo.repository.EmployeeRepository;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    Logger LOGGER;
    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    @RestClient
    CompanyProxy companyProxy;

    @Override
    @ReactiveTransactional
    public Uni<Employee> save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.fromDTO(employeeDTO);
        return employeeRepository.persist(employee);
    }

    @Override
    @Fallback(fallbackMethod = "fallbackGet")
    public Uni<Employee> get(Long id) {
        return employeeRepository.findById(id).flatMap(employee ->
            companyProxy.getCompany(employee.getCompanyCode()).flatMap(companyDTO -> {
                Company company = new Company();
                employee.setCompany(company);
                return Uni.createFrom().item(employee);
            }));
    }

    @Override
    @Fallback(fallbackMethod = "fallbackGetAll")
    public Uni<List<Employee>> getAll() {
        return employeeRepository.listAll().flatMap(employees ->
            companyProxy.getCompanies().flatMap(companies -> {
                employees.forEach(employee -> {
                    Company company = companies.stream().filter(c -> c.getCode().equals(employee.getCompanyCode())).findFirst().orElse(null);
                    employee.setCompany(company);
                });
                return Uni.createFrom().item(employees);
            })
        );
    }

    private Uni<Employee> fallbackGet(Long id){
        LOGGER.warn("Falling back on get employee " + id);
        throw new IllegalStateException();
    }

    private Uni<List<Employee>> fallbackGetAll(){
        LOGGER.warn("Falling back on get all employee");
        throw new IllegalStateException();
    }
}
