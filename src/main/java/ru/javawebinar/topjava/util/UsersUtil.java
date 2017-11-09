package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User("Vasya Pupkin", "vasya@mail.ru", "123", Role.ROLE_USER, Role.ROLE_USER),
            new User("Ivan Ivanov", "ivanov@mail.ru", "456", Role.ROLE_ADMIN, Role.ROLE_ADMIN),
            new User("Igor Nekto", "igor@mail.ru", "789", Role.ROLE_USER, Role.ROLE_USER),
            new User("Pasha Kruglov", "pasha@mail.ru", "159", Role.ROLE_USER, Role.ROLE_USER)
    );
}
