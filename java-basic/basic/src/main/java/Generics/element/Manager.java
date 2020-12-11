package Generics.element;

/**
 * Created by Defias on 2020/07.
 * Description:
 */

//leader类
public class Manager extends Employee {
    private double bonus;


    public Manager(String name, double salary, int hireyear, int hiremonth, int hireday) {
        super(name, salary, hireyear, hiremonth, hireday);
        bonus = 0;
    }

    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }

    public double getBonus() {
        return bonus;
    }
}