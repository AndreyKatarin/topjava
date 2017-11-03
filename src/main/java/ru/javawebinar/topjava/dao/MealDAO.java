package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.test.MealList;

import java.util.List;

public class MealDAO extends AbstractDAO<Meal, Integer> {

    private MealList mealList;

    public MealDAO() {
        mealList = MealList.getInstance();
    }

    @Override
    public List<Meal> getAll() {
        return mealList;
    }

    @Override
    public Meal getEntityByID(Integer id) {
        return mealList.get(id);
    }

    @Override
    public void insert(Meal entity) {
        mealList.add(entity);
    }

    @Override
    public void update(Meal entity) {
        mealList.update(entity);
    }

    @Override
    public void delete(Meal meal) {
        mealList.remove(meal);
    }

    public int getGeneratedId() {
        return mealList.generateId();
    }
}
