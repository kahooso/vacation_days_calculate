package com.example.calculate_vacation_days.services;

import com.example.calculate_vacation_days.models.Holiday;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HolidayService {
    private final Map<LocalDate, Holiday> holidayMap = new HashMap<>();

    public HolidayService() {
        addHolidays();
    }

    private void addHolidays() {
        // Добавляем ежегодные праздники
        addHoliday(1, 1, "Новогодние праздники");
        addHoliday(1, 2, "Новогодние праздники");
        addHoliday(1, 3, "Новогодние праздники");
        addHoliday(1, 4, "Новогодние праздники");
        addHoliday(1, 5, "Новогодние праздники");
        addHoliday(1, 6, "Новогодние праздники");
        addHoliday(1, 7, "Рождество Христово");
        addHoliday(2, 23, "День защитника Отечества");
        addHoliday(3, 8, "Международный женский день");
        addHoliday(5, 1, "Праздник Весны и Труда");
        addHoliday(5, 9, "День Победы");
        addHoliday(6, 12, "День России");
        addHoliday(11, 4, "День народного единства");
    }

    public void addHoliday(int month, int day, String name) {
        holidayMap.put(LocalDate.of(1, month, day), new Holiday(month, day, name));
    }

    public boolean isHoliday(LocalDate date) {
        Holiday holiday = holidayMap.get(LocalDate.of(1, date.getMonthValue(), date.getDayOfMonth()));
        return holiday != null;
    }
}
