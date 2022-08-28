package com.example.demo.person;

class PersonNotFoundException extends RuntimeException {
    PersonNotFoundException(Long id) {
        super("Could not find person " + id);
    }
}
