package ru.p11505.practic2024.course3.workingtimecontrol.view;

import ru.p11505.practic2024.course3.workingtimecontrol.User;
import ru.p11505.practic2024.course3.workingtimecontrol.WorkTimeTracker;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

import static ru.p11505.practic2024.course3.workingtimecontrol.WorkEvent.*;

public class Console {
    public static void main(String[] args) {
        WorkTimeTracker tracker;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите норму рабочего времени в часах:");
        int normHours = scanner.nextInt();
        scanner.nextLine();
        Duration workNorm = Duration.ofHours(normHours);
        System.out.println("Введите время начала работы (часы:минуты, например 09:00):");
        String startWorkInput = scanner.nextLine();
        LocalTime startWorkTime = LocalTime.parse(startWorkInput);

        User user = new User(startWorkTime);
        tracker = new WorkTimeTracker(workNorm, user);
        //scanner.nextLine(); // Очистка буфера после nextInt()

        boolean exit = false;
        while (!exit) {
            System.out.println("Что вы хотите сделать? \n1: Добавить событие \n2: Получить отчет \n3: Выход");
            int value = scanner.nextInt();
            switch (value) {
                case 1:
                    addEvent(scanner, tracker);
                    break;
                case 2:
                    tracker.calculateTotalWorkTime();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }

    private static void addEvent(Scanner scanner, WorkTimeTracker tracker) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Введите событие: \n1: IN\n2: OUT\n3: LUNCH\n4: PAUSE\n5: Назад");
            int value = scanner.nextInt();
            switch (value) {
                case 1:
                    tracker.addEvent(getEventTime(scanner), EVENT_TYPE_IN);
                    break;
                case 2:
                    tracker.addEvent(getEventTime(scanner), EVENT_TYPE_OUT);
                    break;
                case 3:
                    tracker.addEvent(getEventTime(scanner), EVENT_TYPE_LUNCH);
                    break;
                case 4:
                    tracker.addEvent(getEventTime(scanner), EVENT_TYPE_PAUSE);
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }

    private static LocalTime getEventTime(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите время регистрации события (часы:минуты, например 09:00):");
        String eventTime = scanner.nextLine();
        return LocalTime.parse(eventTime);
    }
}
