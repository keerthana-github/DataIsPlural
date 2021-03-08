package project2;

import java.net.URL;
import java.util.ArrayList;

/**
 * DataSetList class stores a collection of DataSet objects.
 * This class inherits from ArrayList<DataSet>. It adds functionality to get
 * values from a list by seeing if the title, description, or URL contains
 * specific keywords.
 * 
 * This class is sorted by dates and titles.
 * 
 * @author Keerthana Manivasakan
 * 
 */

@SuppressWarnings("serial")
public class DataSetList extends ArrayList<DataSet> {
    /**
     * Default constructor to create array list of DataSet objects.
     */
    public DataSetList() {
    }

    
    /**
     * Search through the DataSetList to see if there are any titles with the
     * keyword provided.
     * @param keyword the name of the value to see if it exists within the list
     * @return the list of DataSet objects that include the keyword in it's title
     * unless no values exist which in that case we then return null. List is sorted.
     * @throws IllegalArgumentException if the keyword is invalid
     */
    public DataSetList getByTitle(String keyword) throws IllegalArgumentException {
        if (keyword == null)
            throw new IllegalArgumentException("Keyword cannot be null.");
        if (keyword.equals(""))
            throw new IllegalArgumentException("Keyword cannot be empty.");
        DataSetList key = new DataSetList();
        for (DataSet title : this) {
            String t = title.title;
            if (t.toLowerCase().contains(keyword.toLowerCase())) {
                key.add(title);
            }
        }
        // sorting method to return list sorted by date and title
        if (key.size() > 1) {
            for (int i = 0; i < key.size(); i++) {
                for (int j = i + 1; j < key.size(); j++) {
                    DataSet tmp;
                    if (key.get(i).compareTo(key.get(j)) > 0) {
                        tmp = key.get(i);
                        key.set(i, key.get(j));
                        key.set(j, tmp);
                    }
                }
            }
            return key;
        } else if (key.size() == 1) {
            return key;
        } else
            return null;
    }
    /**
     * Search through the DataSetList to see if there are any descriptions with the
     * keyword provided.
     * @param keyword the name of the value to see if it exists within the list
     * @return the list of DataSet objects that include the keyword in it's description
     * unless no values exist which in that case we then return null. Note the list is
     * sorted.
     * @throws IllegalArgumentException if the keyword is invalid
     */
    public DataSetList getByDescription(String keyword) throws IllegalArgumentException {
        if (keyword == null)
            throw new IllegalArgumentException("Keyword cannot be null.");
        if (keyword.equals(""))
            throw new IllegalArgumentException("Keyword cannot be empty.");
        DataSetList key = new DataSetList();
        for (DataSet description : this) {
            String d = description.description;
            if (d.toLowerCase().contains(keyword.toLowerCase())) {
                key.add(description);
            }
        }
        // sorting method
        if (key.size() > 1) {
            for (int i = 0; i < key.size(); i++) {
                for (int j = i + 1; j < key.size(); j++) {
                    DataSet tmp;
                    if (key.get(i).compareTo(key.get(j)) > 0) {
                        tmp = key.get(i);
                        key.set(i, key.get(j));
                        key.set(j, tmp);
                    }
                }
            }
            return key;
        } else if (key.size() == 1) {
            return key;
        } else
            return null;
    }
    /**
     * Search through the DataSetList to see if there are any URL with the
     * keyword provided.
     * @param keyword the name of the value to see if it exists within the list
     * @return the list of DataSet objects that include the keyword in it's URL
     * unless no values exist which in that case we then return null. The list is
     * sorted.
     * @throws IllegalArgumentException if the keyword is invalid
     */
    public DataSetList getByURL(String keyword) throws IllegalArgumentException {
        if (keyword == null)
            throw new IllegalArgumentException("Keyword cannot be null.");
        if (keyword.equals(""))
            throw new IllegalArgumentException("Keyword cannot be empty.");
        DataSetList key = new DataSetList();
        for (DataSet url : this) {
            ArrayList<URL> d = url.links;
            for (URL u : d) {
                if (u.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    key.add(url);
                }
            }
        }
        // sortign method
        if (key.size() > 1) {
            for (int i = 0; i < key.size(); i++) {
                for (int j = i + 1; j < key.size(); j++) {
                    DataSet tmp;
                    if (key.get(i).compareTo(key.get(j)) > 0) {
                        tmp = key.get(i);
                        key.set(i, key.get(j));
                        key.set(j, tmp);
                    }
                }
            }
            return key;
        } else if (key.size() == 1) {
            return key;
        } else
            return null;
    }

}
