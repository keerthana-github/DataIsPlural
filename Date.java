package project2;

/**
 * This class represents a date based on the following format:
 * year, month, day
 * 
 * @author Keerthana Manivasakan
 * 
 */
public class Date implements Comparable<Date> {

    // parts of the date
    int year;
    int month;
    int day;

    /**
     * Constructs a new date object with specified year, month, and day.
     * @param year value that represents a year
     * @param month value that represents one of the 12 months
     * @param day value that represents a day corresponding to the months
     * @throws IllegalArgumentException if year, month, or day are invalid
     */
    public Date(int year, int month, int day) throws IllegalArgumentException {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    /**
     * Set the value for year.
     * @param y integer that is a year
     * @throws IllegalArgumentException if year is not a positive integer.
     */
    private void setYear(int y) throws IllegalArgumentException {
        if (y <= 0)
            throw new IllegalArgumentException("Invalid year. Must be a positive integer.");
        else
            year = y;
    }

    /**
     * Sets the value for month.
     * @param m integer that represents a month
     * @throws IllegalArgumentException if year is not a number between 1 - 12
     */
    private void setMonth(int m) throws IllegalArgumentException {
        if (m < 1 || m > 12)
            throw new IllegalArgumentException("Invalid month. Must be within range 1 - 12.");
        else
            month = m;
    }

    /**
     * Sets the day based on what month it is.
     * @param d integer value that represents the day of the month
     * @throws IllegalArgumentException if day does not correspond with month.
     */
    private void setDay(int d) throws IllegalArgumentException {

        // anuary, March, May, July, August, October, December.
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (d < 1 || d > 31) {
                throw new IllegalArgumentException("Invalid day. The month you selected can only have 31 days.");
            } else
                day = d;
        }

        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (d < 1 || d > 30) {
                throw new IllegalArgumentException("Invalid day. The month you selected can only have 30 days.");
            } else
                day = d;
        }

        else if (month == 2) {
            if (year % 4 != 0) {
                if (day == 29) {
                    throw new IllegalArgumentException("Year not a leap year.");
                } else if (d > 28 || d < 1) {
                    throw new IllegalArgumentException("Year has 28 days for the month of February.");
                } else
                    day = d;
            }
            if (year % 4 == 0) {
                if (d > 29 || d < 1) {
                    throw new IllegalArgumentException("Year is a leap year has 29 days for the month of February.");
                } else
                    day = d;
            }

        }
    }
    /**
     * Indicates whether some object obj is the same as this date.
     * Objects are the same if they represent identical dates.
     * @return a boolean value, true if identical, false if different
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Date))
            return false;
        Date other = (Date) obj;
        if (this.year == other.year) {
            if (this.month == other.month)
                if (this.day == other.day)
                    return true;
                else
                    return false;
            else
                return false;
        } else if (this.year == 0) {
            if (other.year != 0)
                return false;
        } else
            return false;
        return true;
    }

    /**
     * Provides string representation of the date object
     * @return string representation of date object
     */
    @Override
    public String toString() {
        String y = Integer.toString(year);
        if (y.length() < 4) {
            if (y.length() == 1)
                y = "000" + y;
            if (y.length() == 2)
                y = "00" + y;
            if (y.length() == 3)
                y = "0" + y;
        }
        String d = Integer.toString(day);
        if (d.length() == 1) {
            d = "0" + d;
        }
        String m = Integer.toString(month);
        if (m.length() == 1) {
            m = "0" + m;
        }
        return y + "-" + m + "-" + d;
    }
    /**
     * Determines which date object is bigger (more recent) based on year, 
     * month, and day. 
     * @return an int, 0 if the same, > 0 if bigger, < 0 if smaller
     */
    @Override
    public int compareTo(Date o) {
        if (this.year == o.year) {
            if (this.month == o.month) {
                if (this.day == o.day)
                    return 0;
                else
                    return Integer.compare(this.day, o.day);
            } else
                return Integer.compare(this.month, o.month);
        } else
            return Integer.compare(this.year, o.year);
    }
}
