package com.streaksmart.ui;

import com.streaksmart.model.Habit;
import com.streaksmart.service.HabitService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final HabitService service;
    private final Scanner scanner;

    public ConsoleUI(HabitService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addHabit();
                    case "2" -> listHabits();
                    case "3" -> markCompletion();
                    case "4" -> showProgressSummary();
                    case "5" -> removeHabit();
                    case "0" -> {
                        running = false;
                        System.out.println("Goodbye from StreakSmart.");
                    }
                    default -> System.out.println("Invalid menu option. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("===== StreakSmart =====");
        System.out.println("1. Add habit");
        System.out.println("2. View habits");
        System.out.println("3. Mark habit complete");
        System.out.println("4. View progress summary");
        System.out.println("5. Remove habit");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void addHabit() {
        System.out.print("Habit name: ");
        String name = scanner.nextLine();

        System.out.print("Description (optional): ");
        String description = scanner.nextLine();

        System.out.print("Start date (YYYY-MM-DD, blank for today): ");
        String inputDate = scanner.nextLine().trim();
        LocalDate startDate = inputDate.isBlank() ? LocalDate.now() : LocalDate.parse(inputDate);

        Habit habit = service.addHabit(name, description, startDate);
        System.out.println("Created habit: " + habit.getSummary());
    }

    private void listHabits() {
        List<Habit> habits = service.listHabits();

        if (habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }

        for (Habit habit : habits) {
            System.out.printf(
                    "#%d | %s | Started: %s | Completed today: %s%n",
                    habit.getId(),
                    habit.getName(),
                    habit.getStartDate(),
                    habit.isCompletedOn(LocalDate.now()) ? "Yes" : "No"
            );
        }
    }

    private void markCompletion() {
        System.out.print("Habit ID: ");
        int habitId = Integer.parseInt(scanner.nextLine());

        System.out.print("Completion date (YYYY-MM-DD, blank for today): ");
        String inputDate = scanner.nextLine().trim();
        LocalDate completionDate = inputDate.isBlank() ? LocalDate.now() : LocalDate.parse(inputDate);

        boolean recorded = service.markCompleted(habitId, completionDate);
        if (recorded) {
            System.out.println("Completion recorded.");
        } else {
            System.out.println("Completion was not recorded. The habit may not exist or the date may already be stored.");
        }
    }

    private void showProgressSummary() {
        System.out.println(service.generateProgressReport());
    }

    private void removeHabit() {
        System.out.print("Habit ID to remove: ");
        int habitId = Integer.parseInt(scanner.nextLine());

        boolean removed = service.removeHabit(habitId);
        System.out.println(removed ? "Habit removed." : "Habit not found.");
    }
}
