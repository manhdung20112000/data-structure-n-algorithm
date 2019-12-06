/**
 * A data structure example
 */
public class Date implements Comparable<Date>{
    private int day, month, year;

    public Date(){}

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void data (int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void print() {
        System.out.println("Date: " + this.day + ' ' + this.month + ' ' + this.year);
    }

    @Override
    public int compareTo(Date o) {
        if (this.year > o.year) return -1; 
        if (this.year < o.year) return 1;
        if (this.month > o.month) return -1;
        if (this.month < o.month) return 1;
        if (this.day > o.day) return -1;
        if (this.day < o.day) return 1;
        
        return 0;
    }
}