import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public final class Date implements Comparable<Date> {
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

    public boolean equal(Object y) {                        //Why Object? Experts still debate
        if (y == this) return true;                         //optimize for true object equality
        
        if (y == null) return false;                        //check for null

        if (y.getClass() != this.getClass()) return false;  //objects must be in the same class (religon: getClass() vs. instanceof())

        Date that = (Date) y;                               //cast is guaranteed to succeed
        if (that.day != this.day) return false;
        if (that.month != this.month) return false;
        if (that.year != this.year) return false;
        return true;
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