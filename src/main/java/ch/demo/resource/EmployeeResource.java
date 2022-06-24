package ch.demo.resource;

import ch.demo.entity.Employee;
import ch.demo.model.EmployeeDTO;
import ch.demo.service.EmployeeService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    @Inject
    Logger LOGGER;


    @Inject
    EmployeeService employeeService;

    @ConfigProperty(name = "seconds.sleep", defaultValue = "0")
    int secondsToSleep = 0;

    @GET
    @Path("/{id}")
    @Retry(maxRetries = 10)
    public Uni<Employee> get( @PathParam("id") Long id) {
        return employeeService.get(id);
    }

    @GET
    @Timeout(250)
    public Uni<List<Employee>> getAll() throws InterruptedException{
        LOGGER.info("Waiting for " + secondsToSleep + " seconds");
        TimeUnit.SECONDS.sleep(secondsToSleep);
        return employeeService.getAll();
    }

    @POST
    public Uni<Employee> create(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }
}
