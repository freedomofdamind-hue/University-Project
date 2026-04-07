package com.streaksmart.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public final class StreakCalculator {
    private StreakCalculator() {
    }

    public static int calculateCurrentStreak(Set<LocalDate> completionDates) {
        if (completionDates == null || completionDates.isEmpty()) {
            return 0;
        }

        int streak = 0;
        LocalDate cursor = LocalDate.now();

        while (completionDates.contains(cursor)) {
            streak++;
            cursor = cursor.minusDays(1);
        }

        return streak;
    }

    public static int calculateLongestStreak(Set<LocalDate> completionDates) {
        if (completionDates == null || completionDates.isEmpty()) {
            return 0;
        }

        TreeSet<LocalDate> sortedDates = new TreeSet<>(completionDates);
        int longest = 1;
        int current = 1;
        LocalDate previous = null;

        for (LocalDate date : sortedDates) {
            if (previous != null) {
                if (previous.plusDays(1).equals(date)) {
                    current++;
                } else {
                    longest = Math.max(longest, current);
                    current = 1;
                }
            }
            previous = date;
        }

        return Math.max(longest, current);
    }

    public static double calculateCompletionRate(Set<LocalDate> completionDates, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            return 0.0;
        }

        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (totalDays <= 0) {
            return 0.0;
        }

        return (completionDates.size() * 100.0) / totalDays;
    }
}
