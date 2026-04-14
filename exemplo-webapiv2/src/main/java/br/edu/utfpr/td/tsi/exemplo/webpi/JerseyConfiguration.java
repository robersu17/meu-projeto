package br.edu.utfpr.td.tsi.exemplo.webpi;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


import br.edu.utfpr.td.tsi.exemplo.webpi.endpoint.UserEndpoint;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@ApplicationPath("/exemplo-webapi")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(UserEndpoint.class);
        register(CorsFilter.class);
    }
}

@Provider
class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(jakarta.ws.rs.container.ContainerRequestContext requestContext, 
                       jakarta.ws.rs.container.ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", 
            "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", 
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
