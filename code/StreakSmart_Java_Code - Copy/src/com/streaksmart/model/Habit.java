package com.streaksmart.model;

import com.streaksmart.service.StreakCalculator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Habit {
    private final int id;
    private final String name;
    private final String description;
    private final LocalDate startDate;
    private final Frequency frequency;
    private final Set<LocalDate> completionDates;

    protected Habit(int id, String name, String description, LocalDate startDate, Frequency frequency) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Habit name cannot be empty.");
        }
        this.id = id;
        this.name = name.trim();
        this.description = description == null ? "" : description.trim();
        this.startDate = Objects.requireNonNull(startDate, "Start date is required.");
        this.frequency = Objects.requireNonNull(frequency, "Frequency is required.");
        this.completionDates = new HashSet<>();
    }

    public boolean markCompleted(LocalDate date) {
        LocalDate completionDate = Objects.requireNonNull(date, "Completion date is required.");
        if (completionDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Completion date cannot be before the habit start date.");
        }
        return completionDates.add(completionDate);
    }

    public boolean isCompletedOn(LocalDate date) {
        return completionDates.contains(date);
    }

    public int getCurrentStreak() {
        return StreakCalculator.calculateCurrentStreak(completionDates);
    }

    public int getLongestStreak() {
        return StreakCalculator.calculateLongestStreak(completionDates);
    }

    public double getCompletionRate() {
        return StreakCalculator.calculateCompletionRate(completionDates, startDate, LocalDate.now());
    }

    public String getSummary() {
        return String.format(
                "#%d %s | Frequency: %s | Current streak: %d | Longest streak: %d | Completion rate: %.2f%%",
                id,
                name,
                frequency.getDisplayName(),
                getCurrentStreak(),
                getLongestStreak(),
                getCompletionRate()
        );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Set<LocalDate> getCompletionDates() {
        return Collections.unmodifiableSet(completionDates);
    }

    @Override
    public String toString() {
        return String.format(
                "Habit{id=%d, name='%s', startDate=%s, frequency=%s, completedToday=%s}",
                id,
                name,
                startDate,
                frequency,
                isCompletedOn(LocalDate.now()) ? "Yes" : "No"
        );
    }
}
