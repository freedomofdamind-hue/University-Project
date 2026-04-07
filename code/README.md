# StreakSmart Java OOP Console Application

## Overview
StreakSmart is a simple habit streak tracker built as a Java Object-Oriented Programming coursework project.

## Features
- Add a habit with a name, description, and start date
- View all habits and whether each one is completed today
- Mark a habit as completed for a specific date
- Prevent duplicate completion entries
- Calculate current and longest streaks
- Show a progress summary
- Remove a habit

## Compile
```bash
javac -d out $(find src -name "*.java")
```

## Run the application
```bash
java -cp out com.streaksmart.Main
```

## Run the tests
```bash
java -cp out com.streaksmart.StreakSmartTest
```
