package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByLoop(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        if (mealList == null || startTime == null || endTime == null)
            return new ArrayList<>();

        Map<LocalDate, Integer> userMealMapPerDay = new HashMap<>();
        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();

        for (UserMeal userMeal: mealList) {
            userMealMapPerDay.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories() +
                    userMealMapPerDay.getOrDefault(userMeal.getDateTime().toLocalDate(), 0));
        }

        for (UserMeal userMeal: mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                userMealWithExceedList.add(
                        new UserMealWithExceed(
                                userMeal.getDateTime(),
                                userMeal.getDescription(),
                                userMeal.getCalories(),
                                userMealMapPerDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay
                        )
                );
        }
        return userMealWithExceedList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {

        if (mealList == null || startTime == null || endTime == null)
            return new ArrayList<>();

        Map<LocalDate, Integer> userMealMapPerDay = mealList.stream().collect(
                Collectors.toMap((UserMeal entry) -> entry.getDateTime().toLocalDate(),
                        UserMeal::getCalories, Integer::sum));

        return mealList.stream().filter((entry) -> TimeUtil.isBetween(entry.getDateTime().toLocalTime(), startTime, endTime))
                .map(
                        entry -> new UserMealWithExceed(
                                entry.getDateTime(),
                                entry.getDescription(),
                                entry.getCalories(),
                                userMealMapPerDay.get(entry.getDateTime().toLocalDate()) > caloriesPerDay
                        )).collect(Collectors.toList());
    }
}
