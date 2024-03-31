package com.example.calculate_vacation_days.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.calculate_vacation_days.services.HolidayService;


import java.time.LocalDate;

@Controller
public class VacationDaysController {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private final HolidayService holidayService;

    public VacationDaysController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/")
    public String showMainPage() {
        return "main";
    }

    @GetMapping("/**") // Обрабатываем все остальные URL-адреса
    public String handleInvalidUrl(Model model) {
        model.addAttribute("message", "Пожалуйста, введите URL-адресс корректно!");
        return "error";
    }

    @GetMapping("/calculate/{averageSalary}/{vacationDays}")
    public String calculateVacationSearch(@PathVariable("averageSalary") Double averageSalary,
                                          @PathVariable("vacationDays") Integer vacationDays,
                                          Model model) {
        return calculateVacationDays(averageSalary, vacationDays, model);
    }

    @GetMapping("/calculate/{averageSalary}/{startDate}/{endDate}") //search
    public String calculateVacationSearchWithDate(@PathVariable("averageSalary") Double averageSalary,
                                                  @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                  Model model) {
        return calculateVacationDaysWithDates(averageSalary, null, startDate, endDate, model);
    }

    @GetMapping("/calculate")
    public String calculateVacationDaysWithDates(@RequestParam(required = false) Double averageSalary,
                                                 @RequestParam(required = false) Integer vacationDays,
                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                 Model model) {
        if (startDate != null && endDate != null && startDate.isBefore(endDate)) {
            long workingDays = calculateWorkingDays(startDate, endDate);
            return calculateVacationDays(averageSalary, (int) workingDays, model);
        }
        return calculateVacationDays(averageSalary, vacationDays, model);
    }

    private long calculateWorkingDays(LocalDate startDate, LocalDate endDate) {
        long workingDays = 0;
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(0)); date = date.plusDays(1)) {
            if (date.getDayOfWeek().getValue() <= 5 && !holidayService.isHoliday(date)) {
                ++workingDays;
            }
        }
        return workingDays;
    }

    private String calculateVacationDays(Double averageSalary, Integer vacationDays, Model model) {
        validateInputData(averageSalary, vacationDays);
        double vacationPayment = averageSalary / AVERAGE_DAYS_IN_MONTH * vacationDays;
        model.addAttribute("payment", (double) Math.round(vacationPayment));
        return "result";
    }

    private void validateInputData(Double averageSalary, Integer vacationDays) {
        if (averageSalary == null || vacationDays == null || averageSalary <= 0 || vacationDays <= 0) {
            throw new IllegalArgumentException("Пожалуйста, введите данные корректно!");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
