package com.streaksmart.repository;

import com.streaksmart.model.Habit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryHabitRepository implements HabitRepository {
    private final List<Habit> habits = new ArrayList<>();

    @Override
    public void add(Habit habit) {
        habits.add(habit);
    }

    @Override
    public Optional<Habit> findById(int id) {
        return habits.stream().filter(habit -> habit.getId() == id).findFirst();
    }

    @Override
    public List<Habit> findAll() {
        return new ArrayList<>(habits);
    }

    @Override
    public boolean remove(int id) {
        return habits.removeIf(habit -> habit.getId() == id);
    }
}
