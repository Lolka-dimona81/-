package ru.p11505.practic2024.course3.workingtimecontrol;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class WorkTimeTracker {
    private List<WorkEvent> events = new ArrayList<>();
    private Duration workNorm; // Норма рабочего времени
    private User user;

    public WorkTimeTracker(Duration workNorm, User user) {
        this.workNorm = workNorm;
        this.user = user;
    }


    public void addEvent(LocalTime time, String eventType) {
        events.add(new WorkEvent(time, eventType));
    }

    public void calculateTotalWorkTime() {
        Duration totalDuration = Duration.ofHours(0);
        LocalTime startWork = null;
        LocalTime endWork = null;
        LocalTime lunchStartTime = null;
        LocalTime lunchEndTime = null;

        for (WorkEvent event : events) {
            switch (event.getEventType()) {
                case WorkEvent.EVENT_TYPE_IN:
                    startWork = event.getTime();
                    break;
                case WorkEvent.EVENT_TYPE_OUT:
                    endWork = event.getTime();
                    break;
                case WorkEvent.EVENT_TYPE_LUNCH:
                    lunchStartTime = event.getTime();
                    break;
                case WorkEvent.EVENT_TYPE_PAUSE:
                    lunchEndTime = event.getTime();
                    break;
                default:
                    break;
            }
        }

        if (startWork == null || endWork == null) {
            System.out.println("Не достаточно данных для вычисления продолжительности рабочего времени");
        }

        checkForLateEntry(startWork);
        totalDuration = Duration.between(startWork, endWork);

        if (lunchStartTime != null && lunchEndTime != null) {
            Duration lunchDuration = Duration.between(lunchStartTime, lunchEndTime);
            totalDuration = totalDuration.minus(lunchDuration);
        }

        checkWorkNorm(totalDuration);
    }

    private void checkForLateEntry(LocalTime entryTime) {
        LocalTime startWorkTime = user.getWorkStartTime(); // Получаем время начала рабочего дня
        if (entryTime.isAfter(startWorkTime)) {
            Duration delay = Duration.between(startWorkTime, entryTime);
            System.out.println("Вы опоздали на " + delay.toMinutes() + " минут.");
        } else {
            System.out.println("Вы пришли вовремя.");
        }
    }

    public Duration checkWorkNorm(Duration totalWorkTime) {
        Duration difference = totalWorkTime.minus(workNorm);
        if (difference.isNegative()) {
            // Недоработка
            System.out.println("Вы недоработали на " + Math.abs(difference.toHours()) + " часов и " +
                    Math.abs(difference.toMinutesPart()) + " минут.");
            return difference.negated(); // Возвращаем положительное значение недоработки
        } else if (difference.isZero()) {
            System.out.println("Вы отработали норму рабочего времени.");
            return Duration.ZERO; // Возвращаем 0, если норма отработана
        } else {
            // Переработка
            System.out.println("Вы переработали на " + difference.toHours() + " часов и " +
                    difference.toMinutesPart() + " минут.");
            return difference; // Возвращаем положительное значение переработки
        }
    }
}