package ru.javawebinar.topjava.dao;

import javafx.print.Collation;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MealDAO extends AbstractDAO<Meal, Integer> {

    private Map<Integer, Meal> mealMap;

    public MealDAO() {
        mealMap = new ConcurrentHashMap<Integer, Meal>(){
            {
                put(0, new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
                put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
                put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
                put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
                put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
                put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
            }
        };
    }

    @Override
    public List<Meal> getAll() {
        return mealMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Meal getEntityByID(Integer id) {
        return mealMap.get(id);
    }

    @Override
    public void insert(Meal entity) {
        mealMap.put(entity.getId(), entity);
    }

    @Override
    public void update(Meal entity) {
        mealMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        mealMap.remove(id);
    }

    public synchronized int generateId() {
        return Collections.max(mealMap.keySet()) + 1;
    }
}
