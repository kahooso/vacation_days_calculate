package com.example.calculate_vacation_days.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class CalculateController {

    @GetMapping("/")
    public String MainPage() {
        return "main";
    }

    @GetMapping("/calculate/{averageSalary}/{vacationDays}")
    public String calculateVacationSearch(@PathVariable("averageSalary") Double averageSalary,
                                        @PathVariable("vacationDays") Integer vacationDays,
                                        Model model) {
        return CalculateVacationDays(averageSalary, vacationDays, model);
    }

    @GetMapping("/calculate")
    public String calculateWithDates(@RequestParam(required = false) Double averageSalary,
                                     @RequestParam(required = false) Integer vacationDays,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate,
                                     Model model) {
        if (startDate != null && endDate != null) {
            long daysBetween = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
            return CalculateVacationDays(averageSalary, (int)daysBetween, model);
        }
        return CalculateVacationDays(averageSalary, vacationDays, model);
    }


    public String CalculateVacationDays(Double averageSalary, Integer vacationDays, Model model) {
        validateInputs(averageSalary, vacationDays);
        double vacationPayment = averageSalary / 29.3 * vacationDays;
        model.addAttribute("payment", Math.round(vacationPayment));
        return "result";
    }

    public void validateInputs(Double averageSalary, Integer vacationDays) {
        if (averageSalary == null || vacationDays == null) {
            throw new IllegalArgumentException("Пожалуйста, введите данные о зарплате и количестве дней отпуска!");
        } else if (averageSalary <= 0 || vacationDays <= 0) {
            throw new IllegalArgumentException("Зарплата и количество дней отпуска должны быть положительными числами!");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
