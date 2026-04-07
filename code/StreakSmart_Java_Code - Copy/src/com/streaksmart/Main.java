package com.streaksmart;

import com.streaksmart.repository.HabitRepository;
import com.streaksmart.repository.InMemoryHabitRepository;
import com.streaksmart.service.HabitFactory;
import com.streaksmart.service.HabitService;
import com.streaksmart.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        HabitRepository repository = new InMemoryHabitRepository();
        HabitFactory factory = new HabitFactory();
        HabitService service = new HabitService(repository, factory);

        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }
}
