package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
        }
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, m -> new ConcurrentHashMap<>());
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        Map<Integer, Meal> map = repository.get(userId);
        map.keySet().removeIf(k -> k == id);
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> map = repository.get(userId);
        return map.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> map = repository.get(userId);

        return map == null ? Collections.EMPTY_LIST : map.values().stream().sorted((m1, m2) -> m1.getDateTime().compareTo(m2.getDateTime())).collect(Collectors.toList());
    }
}

