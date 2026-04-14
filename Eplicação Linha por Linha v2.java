1. Main.java
1: package br.edu.utfpr.td.tsi.exemplo.webpi;           // Define o pacote da classe
2: 
3: import org.springframework.boot.SpringApplication;      // Importa classe do Spring
4: import org.springframework.boot.autoconfigure.SpringBootApplication;
5: 
6: @SpringBootApplication                             // Annotation que marca como app Spring Boot
7: public class Main {                               // Classe principal da aplicação
8: 
9:     public static void main(String[] args) {     // Método main (ponto de entrada)
10:         SpringApplication.run(Main.class, args); // Inicia o app Spring Boot
11:     }
12: }
---
2. JerseyConfiguration.java
1: package br.edu.utfpr.td.tsi.exemplo.webpi;           // Pacote da classe
2: 
3: import org.glassfish.jersey.server.ResourceConfig;    // Import Jersey config
4: import org.springframework.stereotype.Component;     // Annotation Spring (Bean)
5: 
6: import br.edu.utfpr.td.tsi.exemplo.webpi.endpoint.UserEndpoint;
7: import jakarta.ws.rs.ApplicationPath;              // Define path base da API
8: import jakarta.ws.rs.container.ContainerResponseFilter;
9: import jakarta.ws.rs.ext.Provider;
10: import java.io.IOException;
11: 
12: @Component                                       // Registra como Bean Spring
13: @ApplicationPath("/exemplo-webapi")              // Path base da API: /exemplo-webapi
14: public class JerseyConfiguration extends ResourceConfig {
15: 
16:     public JerseyConfiguration() {              // Construtor
17:         register(UserEndpoint.class);            // Registra o endpoint
18:         register(CorsFilter.class);              // Registra filtro CORS
19:     }
20: }
21: 
22: @Provider                                        // Marca como componente Jersey
23: class CorsFilter implements ContainerResponseFilter { // Filtro para respostas CORS
24:     @Override
25:     public void filter(                           // Método que aplica headers CORS
26:         jakarta.ws.rs.container.ContainerRequestContext requestContext, 
27:         jakarta.ws.rs.container.ContainerResponseContext responseContext
28:         throws IOException {
29:         responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");      // Permite origem cruzada
30:         responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
31:         responseContext.getHeaders().add("Access-Control-Allow-Headers", 
32:             "origin, content-type, accept, authorization");
33:         responseContext.getHeaders().add("Access-Control-Allow-Methods", 
34:             "GET, POST, PUT, DELETE, OPTIONS, HEAD");
35:     }
36: }
---
3. UserEndpoint.java
1: package br.edu.utfpr.td.tsi.exemplo.webpi.endpoint; // Pacote do endpoint
2: 
3: import java.util.ArrayList;                        // Importa ArrayList
4: import java.util.List;                           // Importa List
5: 
6: import br.edu.utfpr.td.tsi.exemplo.webpi.modelo.User;
7: import jakarta.ws.rs.Consumes;                   // Annotation: consome dados
8: import jakarta.ws.rs.GET;                         // Annotation: método GET
9: import jakarta.ws.rs.POST;                       // Annotation: método POST
10: import jakarta.ws.rs.Path;                       // Annotation: path do recurso
11: import jakarta.ws.rs.Produces;                   // Annotation: produz dados
12: import jakarta.ws.rs.core.MediaType;             // Tipo midia (JSON)
13: import jakarta.ws.rs.core.Response;              // Resposta HTTP
14: 
15: @Path("usuarios")                               // Path: /usuarios
16: public class UserEndpoint {                     // Classe do endpoint
17: 
18: