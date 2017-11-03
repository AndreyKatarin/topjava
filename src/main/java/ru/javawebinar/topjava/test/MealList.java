package ru.javawebinar.topjava.test;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 03.11.2017.
 */
public class MealList extends ArrayList<Meal> {

    {
        this.add(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        this.add(new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        this.add(new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        this.add(new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        this.add(new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        this.add(new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    private static MealList instance = null;

    private static Object mutex = new Object();

    private MealList() {}

    public static MealList getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null)
                    instance = new MealList();
            }
        }

        return instance;
    }

    public List<Meal> getAll() {
        return this;
    }

    @Override
    public synchronized boolean add(Meal meal) {
        return super.add(meal);
    }

    public Meal get(int id) {
        return this.stream().filter(m -> m.getId() == id).findAny().orElse(null);
    }

    public synchronized void update(Meal meal) {
        int index = this.indexOf(get(meal.getId()));

        this.set(index, meal);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    public synchronized int generateId() {
        return this.size() + 1;
    }

    @Override
    public String toString() {
        return this.stream().map(Object::toString).reduce(" ", String::concat);
    }
}
