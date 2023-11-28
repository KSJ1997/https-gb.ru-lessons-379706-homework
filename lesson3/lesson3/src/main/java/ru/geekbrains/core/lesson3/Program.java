package ru.geekbrains.core.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Абстрактный класс, представляющий базового работника
abstract class BaseEmployee {
    private String name;

    public BaseEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Абстрактный метод для расчёта среднемесячной заработной платы
    public abstract double calculateAverageMonthlySalary();
}

// Класс, представляющий работника с почасовой оплатой
class Freelancer extends BaseEmployee {
    private double hourlyRate;

    public Freelancer(String name, double hourlyRate) {
        super(name);
        this.hourlyRate = hourlyRate;
    }

    // Реализация метода расчёта среднемесячной заработной платы для Freelancer
    @Override
    public double calculateAverageMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
}

// Класс, представляющий работника с фиксированной оплатой
class Worker extends BaseEmployee {
    private double fixedMonthlyPayment;

    public Worker(String name, double fixedMonthlyPayment) {
        super(name);
        this.fixedMonthlyPayment = fixedMonthlyPayment;
    }

    // Реализация метода расчёта среднемесячной заработной платы для Worker
    @Override
    public double calculateAverageMonthlySalary() {
        return fixedMonthlyPayment;
    }
}

// Класс, реализующий интерфейс Comparable для сортировки сотрудников
class EmployeesList implements Iterable<BaseEmployee>, Comparable<EmployeesList> {
    private List<BaseEmployee> employees;

    public EmployeesList(List<BaseEmployee> employees) {
        this.employees = employees;
    }

    // Реализация метода iterator() для использования в цикле foreach
    @Override
    public java.util.Iterator<BaseEmployee> iterator() {
        return employees.iterator();
    }

    // Реализация метода compareTo для сравнения с другим объектом EmployeesList
    @Override
    public int compareTo(EmployeesList o) {
        double sum1 = employees.stream().mapToDouble(BaseEmployee::calculateAverageMonthlySalary).sum();
        double sum2 = o.employees.stream().mapToDouble(BaseEmployee::calculateAverageMonthlySalary).sum();
        return Double.compare(sum1, sum2);
    }

    // Метод для вывода данных о сотрудниках
    public void displayEmployeesInfo() {
        for (BaseEmployee employee : employees) {
            System.out.printf("Name: %s, Salary: %.2f\n", employee.getName(), employee.calculateAverageMonthlySalary());
        }
    }
}

public class Program {
    public static void main(String[] args) {
        // Создание списка сотрудников и добавление в него экземпляров Freelancer и Worker
        List<BaseEmployee> employees = new ArrayList<>();
        employees.add(new Freelancer("Петя", 15.0));
        employees.add(new Worker("Анна", 2000.0));
        employees.add(new Freelancer("Иван", 18.0));

        // Сортировка сотрудников по среднемесячной зарплате
        Collections.sort(employees, Comparator.comparingDouble(BaseEmployee::calculateAverageMonthlySalary));

        // Создание и отображение списка сотрудников с использованием foreach
        EmployeesList employeesList = new EmployeesList(employees);
        employeesList.displayEmployeesInfo();
    }
}
