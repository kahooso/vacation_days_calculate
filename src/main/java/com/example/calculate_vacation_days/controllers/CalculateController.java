package com.example.calculate_vacation_days.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/calculate")
    public String CalculateVacationPay(@RequestParam(required = false) Double averageSalary, @RequestParam(required = false) Integer vacationDays, Model model) {
        if (averageSalary == null || vacationDays == null) {
            model.addAttribute("message", "Пожалуйста, введите данные о зарплате и количестве дней отпуска!");
            return "error";
        } else if (averageSalary <= 0 || vacationDays <= 0)     {
            model.addAttribute("message", "Зарплата и количество дней отпуска должны быть положительными числами!");
            return "error";
        } else {
            double vacationPayment = averageSalary / 29.3 * vacationDays;
            model.addAttribute("payment", (int)vacationPayment);
            return "result";
        }
    }
}
