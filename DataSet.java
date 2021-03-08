package project2;

import java.net.URL;
import java.util.ArrayList;
/**
 * This class represents the object DataSet. The object DataSet has the following 
 * properties: date, title, description, URL, and hattips.
 */
public class DataSet implements Comparable<DataSet> {

    // store properties here
    private Date date;
    String title;
    String description;
    ArrayList<URL> links = new ArrayList<URL>();
    private String hattips = "";
    /**
     * Constructs a new DataSet object with specified title, description and links.
     * @param t is the title of the data
     * @param d is the description of the data
     * @param l is an array of URLs from the data
     * @throws IllegalArgumentException if any of the parameters for title, description,
     * and URL are invalid
     */
    public DataSet(String t, String d, ArrayList<URL> l) throws IllegalArgumentException {
        if (t == null)
            throw new IllegalArgumentException("Title is null.");
        else if (t.equals(""))
            throw new IllegalArgumentException("Title is empty.");
        else if (l == null)
            throw new IllegalArgumentException("List is null.");
        else if (l.size() == 0)
            throw new IllegalArgumentException("List is empty.");
        else if (d == null)
            throw new IllegalArgumentException("Description is null.");
        else if (d.equals(""))
            throw new IllegalArgumentException("Description is empty.");
        else {
            title = t;
            description = d;
            links = l;
        }
    }
    /**
     * Sets the value of the date.
     * @param newdate provides the date input
     * @throws IllegalArgumentException checks to see if date is invalid based on when it is,
     * or if the value is null/invalid
     */
    public void setDate(Date newdate) throws IllegalArgumentException {
        if (newdate == null)
            throw new IllegalArgumentException("Date cannot be null");
        else if (newdate.year < 2000)
            throw new IllegalArgumentException("Date must be greater than or equal to 2000");
        else if (newdate.toString().equals(""))
            throw new IllegalArgumentException("Date is empty.");
        else
            this.date = newdate;
    }

    /**
     * Returns the date of this object.
     * @return the date of this DataSet object
     */
    public Date getDate() {
        return date;

    }

    /**
     * Sets the value for hattips. Checks to see if value exists, or makes it empty.
     * @param newhattips is the hattip value to be used for the hattip.
     */
    public void setHatTips(String newhattips) {
        if (newhattips == null)
            this.hattips = "";
        else if (newhattips.equals(""))
            this.hattips = "";
        else
            this.hattips = newhattips;
    }

    /**
     * Returns the hattips of the DataSet object.
     * @return the hattips of the DataSet object.
     */
    public String getHatTips() {
        return hattips;

    }

    /**
     * Determines which object is bigger based on date and if they are the same/missing,
     * then by title. The comparison with title is case insensitve.
     * @return an int, 0 if the same, > 0 if bigger, < 0 if smaller
     */
    @Override
    public int compareTo(DataSet o) {
        // compared the titles equals ignore case if titles were equal
        if (this.title.equalsIgnoreCase(o.title)) {
            // if dates are there
            if (this.date == null || o.date == null) {
                return this.title.toLowerCase().compareTo(o.title.toLowerCase());
            } else {
                // compare dates
                return this.date.compareTo(o.date);
            }
            // if not there return 0
        }
        return this.title.toLowerCase().compareTo(o.title.toLowerCase());
        // return compare titles
    }
    /**
     * Indicates whether or not two objects are the same based on dates
     * and titles (note, not case sensitive).
     * @return a boolean value, true if the same, false if different
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DataSet))
            return false;
        DataSet other = (DataSet) obj;
        if (this.date == null || other.date == null) {
            if (this.title.equalsIgnoreCase(other.title)) {
                return true;
            }
            return false;
        }
        if (this.date.equals(other.date))
            if (this.title.equalsIgnoreCase(other.title))
                return true;
            else if (this.date == null) {
                if (other.date != null)
                    return false;
            } else
                return false;
        else
            return false;
        return true;
    }

    /**
     * Returns a string representation of this object which includes the name,
     * description, links, and date if it exists.
     * @return the string representation of DataSet
     */
    @Override
    public String toString() {
        StringBuilder strung = new StringBuilder();
        try {
            for (URL link : links) {
                strung.append(link);
                strung.append("\n");
            }
        } catch (NullPointerException ex) {
            System.err.println("Error with links");
        }
        if (date == null)
            return title + "\n" + description + "\n" + strung.toString();
        else
            return date.toString() + "\n" + title + "\n" + description + "\n" + strung.toString();

    }
}
