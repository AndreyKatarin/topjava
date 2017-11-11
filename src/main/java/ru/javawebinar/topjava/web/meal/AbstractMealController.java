package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractMealController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    public Collection<Meal> getAll() {
        log.info("getAll");
        return mealService.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return mealService.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return mealService.create(meal, AuthorizedUser.id());
    }

    public boolean delete(int id) {
        log.info("delete {}", id);
        return mealService.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal) {
        log.info("update {}", meal);
        mealService.update(meal, AuthorizedUser.id());
    }

    public List<MealWithExceed> getBetween(LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(mealService.getAll(AuthorizedUser.id()), startTime, endTime, caloriesPerDay);
    }
}
