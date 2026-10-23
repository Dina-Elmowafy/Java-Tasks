package com.student.task2;

public class AccountServiceImpl implements AccountService {

    private final UserService personService;

    public AccountServiceImpl(UserService personService) {
        this.personService = personService;
    }

    @Override
    public void getSavePerson(String name) {
        personService.save(name);
    }
}

