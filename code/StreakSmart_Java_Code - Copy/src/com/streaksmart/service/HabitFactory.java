package com.streaksmart.service;

import com.streaksmart.model.DailyHabit;
import com.streaksmart.model.Habit;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class HabitFactory {
    private final AtomicInteger nextId = new AtomicInteger(1);

    public Habit createDailyHabit(String name, String description, LocalDate startDate) {
        return new DailyHabit(nextId.getAndIncrement(), name, description, startDate);
    }
}
