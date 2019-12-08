import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Date implements Comparable<Date> {
    protected int day, month, year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return +1;
        return 0;
    }

    @Override
    public String toString() {
        return "Date [" + this.day + "/" + this.month + "/" + this.year + "]";
    }

    public static void main(String[] args) {
        Date[] dates = new Date[10];
        int i=0;
        while(!StdIn.isEmpty()){
            int day = StdIn.readInt(), month = StdIn.readInt(), year = StdIn.readInt();
            dates[i] = new Date(day, month, year);
            i++;
        }

        //test sort function
        
        for (int j=0; j<i; j++){
            StdOut.println(dates[j].toString());
        }
    }
}

/*
test:
20 11 2000
17 3 2005
31 7 1977
25 2 1968

result:
25 2 1968
31 7 1977
20 11 2000
17 3 2005
 */