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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field


        if (mealList == null || startTime == null || endTime == null)
            return null; // Maybe throw a custom exception instead null?

        Map<LocalDate, Integer> userMealMapPerDay = new HashMap<>();


        mealList.stream().forEach(
                (entry) ->  {
                    if (userMealMapPerDay.containsKey(entry.getDateTime().toLocalDate()))
                        userMealMapPerDay.put(entry.getDateTime().toLocalDate(), userMealMapPerDay.get(entry.getDateTime().toLocalDate()) + entry.getCalories());
                    else
                        userMealMapPerDay.put(entry.getDateTime().toLocalDate(), entry.getCalories());
                }
        );

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
