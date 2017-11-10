package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            //adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

            System.out.println("//================ USERS ORDER BY NAME =================//");
            System.out.println(adminUserController.getAll());


            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            System.out.println("//=================CREATE MEAL ================//");
            mealRestController.create(new Meal(LocalDateTime.now(), "Some new meal", 300));
            System.out.println(mealRestController.getAll());
            System.out.println("//==================UPDATE MEAL ===============//");
            mealRestController.update(new Meal(7, LocalDateTime.now(), "Some new meal - updated", 900));
            System.out.println(mealRestController.getAll());
            System.out.println("//================DELETE MEAL=================//");
            mealRestController.delete(7);
            System.out.println(mealRestController.getAll());
        }
    }
}
