package ca.npars.twitterten;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * CorsFilter for allowing CORS access
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
