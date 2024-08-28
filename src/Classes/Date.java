package Classes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Date {
    private String year;
    private String month;
    private String day;

    public Date(String year, String month, String day) {
        if (day.length() < 2) {
            day = "0" + day;
        }
        if (month.length() < 2) {
            month = "0" + month;
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getters
    public int getYear() {
        return Integer.parseInt(year);
    }

    public int getMonth() {
        return Integer.parseInt(month);
    }

    public int getDay() {
        return Integer.parseInt(day);
    }

    //Setters
    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //Methods
    public static Date enterDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the Year of publication: ");
        String year = scanner.next();
        System.out.print("Please Enter the Month of publication: ");
        String month = scanner.next();
        System.out.print("Please Enter the Day of publication: ");
        String day = scanner.next();
        return new Date(year, month, day);
    }

    public static Date fromStringtoDate(String s) {
        String[] temp = s.split("/");
        System.out.println(Arrays.toString(temp));
        String tempYear = temp[0];
        String tempMonth = temp[1];
        String tempDay = temp[2];
        return new Date(tempYear, tempMonth, tempDay);
    }

    public static Date setDueDate() {
        LocalDate currentDate = LocalDate.now().plusDays(14);

        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

        return new Date(String.valueOf(year), String.valueOf(month), String.valueOf(day));
    }

    public static Date resetDueDate() {
        return new Date("0000", "00", "00");
    }

    public static boolean brokeDueDate(Date dueDate) {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        if (dueDate.getYear() <= year) {
            if (dueDate.getMonth() <= month) {
                return dueDate.getDay() <= day;
            }
        }
        return false;
    }

    public String saveStyle() {
        return year +
                "/" + month +
                "/" + day;
    }

    @Override
    public String toString() {
        return year +
                "/" + month +
                "/" + day;
    }
}
