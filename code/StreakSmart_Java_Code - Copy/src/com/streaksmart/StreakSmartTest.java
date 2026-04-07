package com.streaksmart;

import com.streaksmart.model.Habit;
import com.streaksmart.repository.HabitRepository;
import com.streaksmart.repository.InMemoryHabitRepository;
import com.streaksmart.service.HabitFactory;
import com.streaksmart.service.HabitService;

import java.time.LocalDate;

public class StreakSmartTest {
    public static void main(String[] args) {
        HabitRepository repository = new InMemoryHabitRepository();
        HabitService service = new HabitService(repository, new HabitFactory());

        Habit habit = service.addHabit("Study Java", "Complete one revision session", LocalDate.now().minusDays(3));

        assertTrue(service.markCompleted(habit.getId(), LocalDate.now().minusDays(2)), "First completion should succeed.");
        assertTrue(service.markCompleted(habit.getId(), LocalDate.now().minusDays(1)), "Second completion should succeed.");
        assertTrue(service.markCompleted(habit.getId(), LocalDate.now()), "Third completion should succeed.");
        assertTrue(!service.markCompleted(habit.getId(), LocalDate.now()), "Duplicate completion should fail.");

        Habit loaded = service.findHabitOrThrow(habit.getId());
        assertTrue(loaded.getCurrentStreak() == 3, "Current streak should be 3.");
        assertTrue(loaded.getLongestStreak() == 3, "Longest streak should be 3.");
        assertTrue(service.removeHabit(habit.getId()), "Habit removal should succeed.");

        System.out.println("All StreakSmart tests passed.");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("Test failed: " + message);
        }
    }
}
