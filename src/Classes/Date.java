package Classes;

import java.time.LocalDate;

public class Date {
    private String year;
    private String month;
    private String day;

    public Date(String year, String month, String day) {
        if (day.length() < 2){
            day = "0" + day;
        }
        if (month.length() < 2){
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
    public static Date fromStringtoDate(String s){
        StringBuilder tempYear = new StringBuilder();
        StringBuilder tempMonth = new StringBuilder();
        StringBuilder tempDay = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            tempYear.append(s.charAt(i));
        }
        for (int i = 4; i < 6; i++) {
            tempMonth.append(s.charAt(i));
        }
        for (int i = 6; i < 8; i++) {
            tempDay.append(s.charAt(i));
        }
        return new Date(tempYear.toString(), tempMonth.toString(), tempDay.toString());
    }
    public static Date setDueDate(){
        // Get the current date
        LocalDate currentDate = LocalDate.now().plusDays(14);

        // Get the day, month, and year
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

        return new Date(String.valueOf(year),String.valueOf(month),String.valueOf(day));
    }
    public static Date resetDueDate(){
        return new Date("0000", "00", "00");
    }
    public static boolean brokeDueDate(Date dueDate){
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        if (dueDate.getYear() <= year){
            if (dueDate.getMonth() <= month){
                return dueDate.getDay() <= day;
            }
        }
        return false;
    }
    public String saveStyle() {
        return year + month + day;
    }
    @Override
    public String toString() {
        return year +
                "/" + month +
                "/" + day;
    }
}
