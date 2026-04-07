package com.streaksmart.model;

import java.time.LocalDate;

public class DailyHabit extends Habit {
    public DailyHabit(int id, String name, String description, LocalDate startDate) {
        super(id, name, description, startDate, Frequency.DAILY);
    }
}
