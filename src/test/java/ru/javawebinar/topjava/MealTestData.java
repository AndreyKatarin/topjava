package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by DimaZhukov on 20.11.2017.
 */
public class MealTestData {
    public static final int MEAL_ID = ADMIN_ID + 1;

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
}
