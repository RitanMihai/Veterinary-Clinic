package database.dao;

import java.util.List;

public interface DaoI<T> {
    T get(Long id);
    List<T> getAll ();
    void create (T t);
}
