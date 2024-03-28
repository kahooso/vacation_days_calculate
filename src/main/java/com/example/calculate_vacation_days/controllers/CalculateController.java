package com.example.calculate_vacation_days.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class CalculateController {
    @GetMapping("/")
    public String calculate() {
        return "index";
    }
    @GetMapping("/calculate/{averageSalary}/{vacationDays}")
    public String Calculations(@PathVariable("averageSalary") Double averageSalary,
                               @PathVariable("vacationDays")Integer vacationDays,
                               Model model) {
        return calculate(averageSalary, vacationDays, model);
    }

    @GetMapping("/calculate")
    public String AnotherCalculation(@RequestParam(required = false) Double averageSalary,
                                     @RequestParam(required = false) Integer vacationDays,
                                     Model model) {
        return calculate(averageSalary, vacationDays, model);
    }

    public String calculate(Double averageSalary, Integer vacationDays, Model model) {
        if (averageSalary == null || vacationDays == null) {
            model.addAttribute("message", "Пожалуйста, введите данные о зарплате и количестве дней отпуска!");
            return "error";
        } else if (averageSalary <= 0 || vacationDays <= 0)     {
            model.addAttribute("message",   "Зарплата и количество дней отпуска должны быть положительными числами!");
            return "error";
        } else {
            double vacationPayment = averageSalary / 29.3 * vacationDays;
            model.addAttribute("payment", Math.round(vacationPayment));
            return "result";
        }
    }
}
