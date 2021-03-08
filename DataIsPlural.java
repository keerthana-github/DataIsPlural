package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class parses through the data provided based on title, description, and
 * url. This is the interactive section of the program. The single command line
 * argument required for the program is a data set in a CSV format. In the
 * interactive section of the program, the user is able to enter either one or
 * two keywords based on the following choices: title, description, or URL. Then
 * the program responds by returning all queries that include the specifications
 * provided by the user (give that they actually exist).
 * 
 * @author Keerthana Manivasakan
 * 
 */

public class DataIsPlural {
    /**
     * This is the main() method of this program.
     * 
     * @param args array of Strings from the command line when the program begins;
     *             In this case, the string that should be provided as an argument
     *             is the CSV that contains the title, description, and URL data.
     * @throws FileNotFoundException if File does not exist
     * @throws MalformedURLException if URL is incorrectly formatted
     */
    // The DataIsPlural class is the actual program.
    // This is the class that should contain the main method.
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        // verifies if command line arguments exist
        if (args.length == 0) {
            System.err.println("The program needs a file name as an argument.");
            System.exit(1);
        }
        // sets file to args
        File data = new File(args[0]);
        // checks if file exists
        if (!data.exists()) {
            System.err.println("The file " + data.getAbsolutePath() + " does not exist.");
            System.exit(1);
        }
        // checks if we can read the file
        if (!data.canRead()) {
            System.err.println("The file " + data.getAbsolutePath() + " cannot be opened.");
            System.exit(1);
        }
        // sets scanner to open file
        Scanner dataIn = null;
        // open file here
        try {
            dataIn = new Scanner(data);
        } catch (FileNotFoundException e) {
            System.err.println("The file " + data.getAbsolutePath() + " cannot be opened.");
            System.exit(1);
        }

        // save data in the following variables as we read the file
        CSV csv = new CSV(dataIn);
        DataSetList entries = new DataSetList();
        ArrayList<String> list = null;
        String[] parsed;
        String[] url;
        String t = null;
        String d = null;
        String h = null;

        // get rid of header in CSV
        list = csv.getNextRow();

        // iterate through each row in CSV to get list of DataSet elements
        for (int i = 1; i < csv.getNumOfRows(); i++) {

            // invalid row
            try {
                list = csv.getNextRow();
            } catch (NullPointerException ex) {
                // caused by null values
                System.err.println("Error getting row");
                continue;
            }

            // set title, description, and url
            t = list.get(2);
            d = list.get(3);
            ArrayList<URL> l = new ArrayList<URL>();
            url = list.get(4).split("\\r?\\n");
            // handle malformed url's here
            try {
                for (String urlind : url) {
                    l.add(new URL(urlind));
                }
            } catch (MalformedURLException ex) {
                System.err.println("URL error.");
            }
            // try to see if new row can be created unless invalid arguments are provided
            try {
                DataSet row = new DataSet(t, d, l);
                parsed = list.get(0).split("\\.");
                Date newdate = new Date(Integer.parseInt(parsed[0]), Integer.parseInt(parsed[1]),
                        Integer.parseInt(parsed[2]));
                try {
                    row.setDate(newdate);
                } catch (IllegalArgumentException ex) {
                    System.err.println("Error with date entered.");
                }
                if (list.size() == 6) {
                    h = list.get(5);
                    row.setHatTips(h);
                } else
                    row.setHatTips("");
                entries.add(row);
            } catch (IllegalArgumentException ex) {
                // caused by error in arguments from title, description, and links
                System.err.println("Error in arguments provided for DataSet.");
            }

        }

        // interactive mode:
        // obtaining user input
        Scanner input = new Scanner(System.in);
        String user = "";

        // instructions for user
        System.out.println("Welcome the Data Is Plural data explorer!" + "\n");
        System.out.println("You can use the following queries to search through the data: " + "\n" + "title KEYWORD"
                + "\n" + "description KEYWORD" + "\n" + "url KEYWORD" + "\n\n"
                + "You can combine up to two queries to narrow down the results, for example:" + "\n"
                + "title KEYWORD1  url KEYWORD2" + "\n");

        do {
            System.out.println("Enter query or 'quit' to stop:");
            // get value of from the user
            user = input.nextLine();
            // split user value into multiple parts that separate by operation and keyword
            String[] userlist = user.split(" ");
            if (!user.equalsIgnoreCase("quit")) {
                // set new list to contain response to all users queries
                DataSetList values = new DataSetList();
                // if user only has one query
                if (userlist.length == 2) {
                    // title
                    if (userlist[0].equalsIgnoreCase("title")) {
                        values = entries.getByTitle(userlist[1].toLowerCase());
                        // description
                    } else if (userlist[0].equalsIgnoreCase("description")) {
                        values = entries.getByDescription(userlist[1].toLowerCase());
                        // title
                    } else if (userlist[0].equalsIgnoreCase("url")) {
                        values = entries.getByURL(userlist[1].toLowerCase());
                    } else
                        System.out.println("This is not a valid query. Try again.\n");
                    // if user has two queries
                } else if (userlist.length == 4) {
                    // title and description
                    if (userlist[0].equalsIgnoreCase("title") && userlist[2].equalsIgnoreCase("description")) {
                        DataSetList first = entries.getByTitle(userlist[1].toLowerCase());
                        values = first.getByDescription(userlist[3].toLowerCase());
                    }
                    // title and url
                    else if (userlist[0].equalsIgnoreCase("title") && userlist[2].equalsIgnoreCase("url")) {
                        DataSetList first = entries.getByTitle(userlist[1].toLowerCase());
                        values = first.getByURL(userlist[3].toLowerCase());
                    }
                    // title and title
                    else if (userlist[0].equalsIgnoreCase("title") && userlist[2].equalsIgnoreCase("title")) {
                        DataSetList first = entries.getByTitle(userlist[1].toLowerCase());
                        values = first.getByTitle(userlist[3].toLowerCase());
                    }
                    // description and title
                    else if (userlist[0].equalsIgnoreCase("description") && userlist[2].equalsIgnoreCase("title")) {
                        DataSetList first = entries.getByDescription(userlist[1].toLowerCase());
                        values = first.getByTitle(userlist[3].toLowerCase());
                    }
                    // description and url
                    else if (userlist[0].equalsIgnoreCase("description") && userlist[2].equalsIgnoreCase("url")) {
                        DataSetList first = entries.getByDescription(userlist[1].toLowerCase());
                        values = first.getByURL(userlist[3].toLowerCase());
                    }
                    // descprition and description
                    else if (userlist[0].equalsIgnoreCase("description")
                            && userlist[2].equalsIgnoreCase("description")) {
                        DataSetList first = entries.getByDescription(userlist[1].toLowerCase());
                        values = first.getByDescription(userlist[3].toLowerCase());
                    }
                    // url and title
                    else if (userlist[0].equalsIgnoreCase("url") && userlist[2].equalsIgnoreCase("title")) {
                        DataSetList first = entries.getByURL(userlist[1].toLowerCase());
                        values = first.getByTitle(userlist[3].toLowerCase());
                    }
                    // url and description
                    else if (userlist[0].equalsIgnoreCase("url") && userlist[2].equalsIgnoreCase("description")) {
                        DataSetList first = entries.getByURL(userlist[1].toLowerCase());
                        values = first.getByDescription(userlist[3].toLowerCase());
                    }
                    // url and url
                    else if (userlist[0].equalsIgnoreCase("url") && userlist[2].equalsIgnoreCase("url")) {
                        DataSetList first = entries.getByURL(userlist[1].toLowerCase());
                        values = first.getByURL(userlist[3].toLowerCase());
                    } else
                        System.out.println("This is not a valid query. Try again.\n");
                } else
                    System.out.println("This is not a valid query. Try again.\n");

                // print out the queries requested
                try {
                    for (int i = 0; i < values.size(); i++) {
                        System.out.println(values.get(i).toString());
                    }
                } catch (NullPointerException ex) {
                    // caused by an empty list -- no values exist
                    System.err.println("No matches found.\n");
                }
            }
        }

        while (!user.equalsIgnoreCase("quit"));

        input.close();

    }

    // performing some data validation and
    // handling all errors that may occur (in particular, it should handle any
    // exceptions thrown by your other classes and terminate gracefully, if need be,
    // with a friendly error message presented to the user: The program should never
    // just reprint the exception message as a way of handling an exception. These
    // messages are rarely readable to the ordinary user and make it seem like the
    // program crashed in response to the exception.
}
