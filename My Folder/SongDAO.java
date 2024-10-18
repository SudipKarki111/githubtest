/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package songprogram;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;


public class SongDAO {
    
    // Defines a method that returns an ArrayList containing song information
     public ArrayList getSongs() {
        // Initializes an ArrayList to hold Song objects
        ArrayList songs = new ArrayList();
         // Attempt to establish a connection to the database
        Connection conn = getConnection();
         // Declare a ResultSet variable to hold the result of the database query
        ResultSet rs = null;
        try {
            // Creates a Statement object to execute SQL queries
            Statement stmt = conn.createStatement();
             
         // Executes a SQL query to select song details from the Songs table and store the result in rs
            rs = stmt.executeQuery("SELECT id, title,artist,releaseYear,genreID,Album FROM Songs");
          // Iterates over each row in the ResultSet
            while (rs.next()) {
             // Creates a new Song object for each row in the ResultSet,
            // converting string representations of numbers to integers as necessary

                Song s = new Song(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
                        Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(6));
               // Adds the Song object to the songs list

                songs.add(s);
            }
             // Closes the database connection

            conn.close();

        } catch (Exception e) {
           // Catches any exceptions that occur during database operations and print an error message

            System.out.println("Error getting all songs");
        }
       // Returns the list of Song objects

        return songs;

    }

    public Song getSongsByID(int id) {
         // Initializes Song object to null; which  will hold the result if found
        Song s = null;
         //  Again it Establishes a connection to the database
        Connection con = getConnection();
            // Initialize ResultSet to null;  which will hold query result
        ResultSet rs = null;
        try {
            // Again it  Creates a statement to run SQL queries
            Statement stmt = con.createStatement();
          // Executes a query to find a song by its ID and store results in rs
            rs = stmt.executeQuery("SELECT id,title,artist,releaseYear,genreID,album FROM Songs WHERE id=" + id);
            if (rs.next()) {
              // If the song is found, it  creates a Song object with the data
                s = new Song(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
                        Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(6));
                
            }
           // Closes  the database connection again
            con.close();

        } catch (Exception e) {
             // Catches any exceptions and print an error message
            System.out.println("Error finding song with id ");
        }
       // Returns the Song object if found, else return null

        return s;
    }
    
     //This is a method to get a database connection
    public Connection getConnection() {
      // Declares a Connection object and it initializes it to null
        Connection con = null;

        try {
             // It  Loads the SQL Server JDBC driver class which means (checking if there is a network)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//how to connect to server
            // Defining the conection to the URL including the database server address,port, database names, and checks passwords, username that kind of stuff.
            String connectionURL = "jdbc:sqlserver://localhost:1434;DatabaseName=Songs;"
                    + "User=JavaApps;Password=testings";
            //It Establishes connection to the database using the defined URL..
            con = DriverManager.getConnection(connectionURL);
            //It returns the established connection
            return con;
        } catch (Exception e) {
            //Prints the error message and the stack trace if connecting fails.
            System.out.println("Connection Error");
            e.printStackTrace();
        }
        //Returns null if the connection couldnot be established.
        
        return null;

    }

    public void updateSongById(Song s) {
        Connection conn = getConnection();
        if (conn == null) {
            System.out.println("We are unable to connect to database");
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            String update = "UPDATE SONGS SET Title='" + s.getTitile()
                    + "',Artist='" + s.getArtist()
                    + "',Album='" + s.getAlbum()
                    + "'WHERE Id=" + s.getId();
            stmt.executeUpdate(update);
            conn.close();

        } catch (Exception e) {
            System.out.println("There is error updating the song");
            e.printStackTrace();
        }
    }

    public void deleteSong(int id) {
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            String delete = "DELETE from Songs WHERE Id=" + id;
            stmt.executeUpdate(delete);
            conn.close();
        } catch (Exception e) {
            System.out.println(" There is a Error deleting song");
            
            e.printStackTrace();
            /*prints detailed information about an exception.For instance java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
    at Example.main(Example.java:6)*/

        }

    }

}
/*Notes to be followed to connect JDBC(Java Database Connection)
1.Import Packages
2.Load Driver(checking if there is a network)
3.Register Driver(whatever a driver you have to register)
4.COnnection( FOr instance Just dialing the phone to the friend )
5.Create Statement(Just imagine before you say something you thing about it right 
thats creating a statement
6.Execute Statement(once you thought about it say thats your execute Statement)
7.Respone( you will get response)
SO the last process is to close the connection.



      
                   */