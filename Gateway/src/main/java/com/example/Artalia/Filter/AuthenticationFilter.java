 package com.example.Artalia.Filter;

 import com.example.Artalia.Utils.JwtUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.gateway.filter.GatewayFilter;
 import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
 import org.springframework.http.HttpHeaders;
 import org.springframework.stereotype.Service;

 @Service
 public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

     @Autowired
     private RouteValidator routeValidator;

     @Autowired
     private JwtUtils jwtUtil;

     public AuthenticationFilter() {
         super(Config.class);
     }

     @Override
     public GatewayFilter apply(Config config) {
         return ((exchange, chain) -> {
             if (routeValidator.isSecured.test(exchange.getRequest())) {
                 if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                     throw new RuntimeException("Missing authorization header");
                 }

                 String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                 if (authHeader != null && authHeader.startsWith("Bearer ")) {
                     authHeader = authHeader.substring(7);
                 }
                 try {
                     jwtUtil.validateJwtToken(authHeader);
                 } catch (Exception e) {
                     System.out.println("invalid access...!");
                     throw new RuntimeException("Unauthorized access to application");
                 }
             }
             return chain.filter(exchange);
         });
     }

     public static class Config {

     }
 }
