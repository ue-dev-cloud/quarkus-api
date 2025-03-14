package org.acme.quickstart.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public String hello() {
        return "{\"key\": \"Hello GCP World !\"}";
    }
}
