package ch.demo.proxy;

import ch.demo.entity.Company;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface CompanyProxy {
    @GET
    @Path("/code/{code}")
    Uni<Company> getCompany(@PathParam("code") String code);

    @GET
    Uni<List<Company>> getCompanies();
}
