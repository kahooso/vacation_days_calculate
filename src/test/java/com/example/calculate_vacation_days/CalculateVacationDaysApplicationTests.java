package com.example.calculate_vacation_days;

import com.example.calculate_vacation_days.controllers.VacationDaysController;
import com.example.calculate_vacation_days.services.HolidayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CalculateVacationDaysApplicationTests {

	@Mock
	private Model model;

	@Mock
	private HolidayService holidayService;

	@InjectMocks
	private VacationDaysController controller;

	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@Captor
	private ArgumentCaptor<Double> doubleArgumentCaptor;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCalculateVacationSearch() {
		Double averageSalary = 30000.0;
		Integer vacationDays = 3;
		String result = controller.calculateVacationSearch(averageSalary, vacationDays, model);
		assertEquals("result", result);
		verify(model).addAttribute(stringArgumentCaptor.capture(), doubleArgumentCaptor.capture());
		assertEquals("payment", stringArgumentCaptor.getValue());
		assertEquals(3072.0, doubleArgumentCaptor.getValue(), 0.0);
	}

	// throw an exception
	@Test
	public void testInvalidInputCalculateVacationSearch() { //
		Double averageSalary = 30000.0;
		String result = controller.calculateVacationSearch(averageSalary, null, model);
		assertEquals("error", result);
		verify(model).addAttribute(stringArgumentCaptor.capture(), stringArgumentCaptor.capture());
		assertEquals("message", stringArgumentCaptor.getAllValues().get(0));
		assertEquals("Пожалуйста, введите данные корректно!", stringArgumentCaptor.getAllValues().get(1));
	}

	@Test
	public void testCalculateVacationSearchWithDate() {
		Double averageSalary = 30000.0;
		LocalDate startDate = LocalDate.of(2014, 2, 14);
		LocalDate endDate = LocalDate.of(2014, 2, 15);
		String result = controller.calculateVacationDaysWithDates(averageSalary, null, startDate, endDate, model);
		assertEquals("result", result);
		verify(model).addAttribute(stringArgumentCaptor.capture(), doubleArgumentCaptor.capture());
		assertEquals("payment", stringArgumentCaptor.getValue());
		assertEquals(1024.0, doubleArgumentCaptor.getValue(), 0.0);
	}

	// throw an exception
	@Test
	public void testInvalidInputCalculateVacationSearchWithDate() {
		Double averageSalary = 30000.0;
		LocalDate endDate = LocalDate.of(2024, 2, 14);
		String result = controller.calculateVacationDaysWithDates(averageSalary, null, null, endDate, model);
		assertEquals("error", result);
		verify(model).addAttribute(stringArgumentCaptor.capture(), stringArgumentCaptor.capture());
		assertEquals("message", stringArgumentCaptor.getAllValues().get(0));
		assertEquals("Пожалуйста, введите данные корректно!", stringArgumentCaptor.getAllValues().get(1));
	}
}
