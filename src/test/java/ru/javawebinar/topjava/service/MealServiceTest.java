package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEAL_0);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL_1.getId(), ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5);
    }

    @Test
    public void getBetweenDates() throws Exception {
        assertMatch(
                service.getBetweenDates(
                        LocalDate.of(2015, Month.MAY, 30),
                        LocalDate.of(2015, Month.MAY, 30), USER_ID
                ),
                MEAL_2, MEAL_1, MEAL_0
        );
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        assertMatch(
                service.getBetweenDateTimes(
                        LocalDateTime.of(LocalDate.of(2015, Month.MAY, 30), LocalTime.of(12, 0)),
                        LocalDateTime.of(LocalDate.of(2015, Month.MAY, 30), LocalTime.of(19, 0)),
                        USER_ID
                ),
                MEAL_1
        );
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), MEAL_0, MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5);
    }

    @Test
    public void update() throws Exception {
        final Meal MEAL_1_UPDATED = new Meal(MEAL_1.getId(), MEAL_1.getDateTime(), "Завтрак-updated", 5000);
        service.update(MEAL_1_UPDATED, USER_ID);
        assertMatch(service.get(MEAL_1.getId(), USER_ID), MEAL_1_UPDATED);
    }

    @Test
    public void create() throws Exception {
        final Meal MEAL_CREATED = new Meal(null, LocalDateTime.now(), "Ужин после 6....", 2000);
        service.create(MEAL_CREATED, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL_0, MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5, MEAL_CREATED);
    }
}