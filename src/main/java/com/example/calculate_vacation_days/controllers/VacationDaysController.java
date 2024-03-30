package com.example.calculate_vacation_days.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class VacationDaysController {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    // main page
    @GetMapping("/")
    public String showMainPage() {
        return "main";
    }

    // search + specific number of vacation days
    @GetMapping("/calculate/{averageSalary}/{vacationDays}")
    public String calculateVacationSearch(@PathVariable("averageSalary") Double averageSalary,
                                          @PathVariable("vacationDays") Integer vacationDays,
                                          Model model) {
        return calculateVacationDays(averageSalary, vacationDays, model);
    }

    // search + specific vacation period
    @GetMapping("/calculate/{averageSalary}/{startDate}/{endDate}") //search
    public String calculateVacationSearchWithDate(@PathVariable("averageSalary") Double averageSalary,
                                                  @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                  Model model) {
        return calculateVacationDaysWithDates(averageSalary, null, startDate, endDate, model);
    }

    // main form
    @GetMapping("/calculate")
    public String calculateVacationDaysWithDates(@RequestParam(required = false) Double averageSalary,
                                                 @RequestParam(required = false) Integer vacationDays,
                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                 Model model) {
        if (startDate != null && endDate != null && startDate.isBefore(endDate)) {
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            return calculateVacationDays(averageSalary, (int) daysBetween, model);
        }
        return calculateVacationDays(averageSalary, vacationDays, model);
    }

    // calculate function
    private String calculateVacationDays(Double averageSalary, Integer vacationDays, Model model) {
        validateInputData(averageSalary, vacationDays);
        double vacationPayment = averageSalary / AVERAGE_DAYS_IN_MONTH * vacationDays;
        model.addAttribute("payment", Math.round(vacationPayment));
        return "result";
    }

    // validation
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
