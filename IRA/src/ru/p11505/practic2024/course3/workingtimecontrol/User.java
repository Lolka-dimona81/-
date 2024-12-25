package ru.p11505.practic2024.course3.workingtimecontrol;

import java.time.LocalTime;

public class User {
    private LocalTime workStartTime;// Начало рабочего дня

    public User(LocalTime workStartTime) {
        this.workStartTime = workStartTime;
    }

    public LocalTime getWorkStartTime() {
        return workStartTime;
    }
}