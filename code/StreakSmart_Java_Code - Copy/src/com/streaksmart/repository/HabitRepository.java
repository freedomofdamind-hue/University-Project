package com.streaksmart.repository;

import com.streaksmart.model.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitRepository {
    void add(Habit habit);
    Optional<Habit> findById(int id);
    List<Habit> findAll();
    boolean remove(int id);
}
