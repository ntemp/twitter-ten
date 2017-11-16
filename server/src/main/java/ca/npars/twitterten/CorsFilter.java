package ca.npars.twitterten;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * CorsFilter for allowing CORS access. Only really necessary for dev testing at the moment.
 * Should be removed in a production environment where CORS is not necessary.
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {
    private final String HEADERS = "Origin, Content-Type, Accept";

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers", HEADERS);
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD");
    }
}
