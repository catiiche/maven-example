package jpa.entity.coursework4;

import jpa.specification.Specification;

import java.util.List;

public interface Dao<T, PK> {
    void add(T t);
    void update(T t);
    T getByPK(PK pk);
    void delete(T t);
    void deleteByPK(PK pk);



   // List<T> getBySpecification(Specification<T> specification); // метод получения списка по какому-то условию
    // вместо того, чтобы писать много разных методов на получение списков по опред условиям - пишем метод
    // а условия формируются уже через объекты


}


