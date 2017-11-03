package ru.javawebinar.topjava.dao;

import java.util.List;


public abstract class AbstractDAO<E, K> {

    public abstract List<E> getAll();
    public abstract E getEntityByID(K id);
    public abstract void insert(E entity);
    public abstract void update(E entity);
    public abstract void delete(E entity);
}
