package com.streaksmart.service;

import com.streaksmart.model.Habit;
import com.streaksmart.repository.HabitRepository;

import java.time.LocalDate;
import java.util.List;

public class HabitService {
    private final HabitRepository repository;
    private final HabitFactory factory;

    public HabitService(HabitRepository repository, HabitFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public Habit addHabit(String name, String description, LocalDate startDate) {
        Habit habit = factory.createDailyHabit(name, description, startDate);
        repository.add(habit);
        return habit;
    }

    public List<Habit> listHabits() {
        return repository.findAll();
    }

    public boolean markCompleted(int habitId, LocalDate date) {
        return repository.findById(habitId)
                .map(habit -> habit.markCompleted(date))
                .orElse(false);
    }

    public boolean removeHabit(int habitId) {
        return repository.remove(habitId);
    }

    public Habit findHabitOrThrow(int habitId) {
        return repository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit with ID " + habitId + " not found."));
    }

    public String generateProgressReport() {
        List<Habit> habits = listHabits();
        if (habits.isEmpty()) {
            return "No habits have been created yet.";
        }

        StringBuilder report = new StringBuilder("Progress Summary\n");
        report.append("====================\n");

        for (Habit habit : habits) {
            report.append(habit.getSummary()).append("\n");
        }

        return report.toString();
    }
}
