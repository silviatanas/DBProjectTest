package dbpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB2Test {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public void openConnection(){

// Step 1: Load IBM DB2 JDBC driver

        try {

            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());

        }

        catch(Exception cnfex) {

            System.out.println("Problem in loading or registering IBM DB2 JDBC driver");

            cnfex.printStackTrace();
        }

// Step 2: Opening database connection


        try {

            connection = DriverManager.getConnection("jdbc:db2://62.44.108.24:50000/SAMPLE", "db2admin", "db2admin");

            statement = connection.createStatement();

        }

        catch(SQLException s){

            s.printStackTrace();

        }

    }

    public void closeConnection(){

        try {

            if(null != connection) {

                // cleanup resources, once after processing

                resultSet.close();

                statement.close();


                // and then finally close connection

                connection.close();

            }

        }

        catch (SQLException s) {

            s.printStackTrace();

        }

    }

    public void select(String stmnt, int column) {

        try{

            resultSet = statement.executeQuery(stmnt);

            String result = "";

            while(resultSet.next()) {

                for (int i = 1; i <= column; i++) {

                    result += resultSet.getString(i);

                    if (i == column) result += " \n";
                    else             result += ", ";
                }
            }

            System.out.println("Executing query: " + stmnt + "\n");
            System.out.println("Result output \n");
            System.out.println("---------------------------------- \n");
            System.out.println(result);

        }
        catch (SQLException s)
        {
            s.printStackTrace();
        }

    }

    public void insert(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }

        catch (SQLException s){

            s.printStackTrace();

        }

        System.out.println("Successfully inserted!");

    }


    public void delete(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }

        catch (SQLException s){

            s.printStackTrace();

        }

        System.out.println("Successfully deleted!");

    }

    public static void main(String[] args) {

        DB2Test db2Obj = new DB2Test();
        String stmnt = "";

        // CLASSROOM
        String CLASSROOM_ID = "";

        // SUBJECT
        String SUBJECT_NAME = "";

        // TEACHER
        String TEACHER_ID = "";
        String TEACHER_NAME = "";
        String TEACHER_NAMESUBJECT = "";

        // CLASS
        String CLASS_ID = "";
        String CLASS_CLASS = "";
        String CLASS_TEACHERID = "";

        // PERIOD
        String PERIOD_IDCLASSROOM = "";
        String PERIOD_IDCLASS = "";
        String PERIOD_CLASSCLASS = "";
        String PERIOD_IDTEACHER = "";
        String PERIOD_NAMESUBJECT = "";
        String PERIOD_HRSTART = "";
        String PERIOD_HREND = "";

        db2Obj.openConnection();

        String choice;
        Scanner input = new Scanner(System.in);
        for(;;) {
            choice = input.nextLine();
            if (choice.equals("EXIT"))
                break;
            if (choice.equals("LIST")) {
                System.out.println("Enter table name:");
                System.out.println("--CLASSROOM, SUBJECT, TEACHER, CLASS, PERIOD--");

                choice = input.nextLine();

                if (choice.equals("CLASSROOM")) {
                    System.out.println("TABLE CLASSROOM");

                    stmnt = "SELECT * FROM FN72014.CLASSROOM";
                    db2Obj.select(stmnt, 1);
                } else if (choice.equals("SUBJECT")) {
                    System.out.println("TABLE SUBJECT");

                    stmnt = "SELECT * FROM FN72014.SUBJECT";
                    db2Obj.select(stmnt, 1);
                } else if (choice.equals("TEACHER")) {
                    System.out.println("TABLE TEACHER");

                    stmnt = "SELECT * FROM FN72014.TEACHER";
                    db2Obj.select(stmnt, 3);
                } else if (choice.equals("CLASS")) {
                    System.out.println("TABLE CLASS");

                    stmnt = "SELECT * FROM FN72014.CLASS";
                    db2Obj.select(stmnt, 3);
                } else if (choice.equals("PERIOD")) {
                    System.out.println("TABLE PERIOD");

                    stmnt = "SELECT * FROM FN72014.PERIOD";
                    db2Obj.select(stmnt, 7);
                }
            }

            if (choice.equals("INSERT")) {
                System.out.println("Select table");
                choice = input.nextLine();

                switch (choice) {
                    case "CLASSROOM" -> {
                        System.out.println("Enter values for CLASSROOM:");

                        System.out.println("ID: ");
                        CLASSROOM_ID = input.nextLine();

                        stmnt = " INSERT INTO FN72014.CLASSROOM(ID)"
                                + " VALUES ('" + CLASSROOM_ID + "')";
                        db2Obj.insert(stmnt);
                    }
                    case "SUBJECT" -> {
                        System.out.println("Enter values for SUBJECT:");

                        System.out.println("NAME: ");
                        SUBJECT_NAME = input.nextLine();

                        stmnt = " INSERT INTO FN72014.SUBJECT(NAME)"
                                + " VALUES ('" + SUBJECT_NAME + "')";
                        db2Obj.insert(stmnt);
                    }
                    case "TEACHER" -> {
                        System.out.println("Enter values for TEACHER:");

                        System.out.println("ID: ");
                        TEACHER_ID = input.nextLine();

                        System.out.println("NAME: ");
                        TEACHER_NAME = input.nextLine();

                        System.out.println("SUBJECT: ");
                        TEACHER_NAMESUBJECT = input.nextLine();

                        stmnt = " INSERT INTO FN72014.TEACHER(ID, NAME, NAMESUBJECT)"
                                + " VALUES ('" + TEACHER_ID + "','" + TEACHER_NAME + "','" + TEACHER_NAMESUBJECT + "')";
                        db2Obj.insert(stmnt);
                    }
                    case "CLASS" -> {
                        System.out.println("Enter values for CLASS:");

                        System.out.println("ID: ");
                        CLASS_ID = input.nextLine();

                        System.out.println("CLASS: ");
                        CLASS_CLASS = input.nextLine();

                        System.out.println("TEACHER ID: ");
                        CLASS_TEACHERID = input.nextLine();

                        stmnt = " INSERT INTO FN72014.CLASS(ID, CLASS, TEACHERID)"
                                + " VALUES ('" + CLASS_ID + "','" + CLASS_CLASS + "','" + CLASS_TEACHERID + "')";
                        db2Obj.insert(stmnt);
                    }
                    case "PERIOD" -> {
                        System.out.println("Enter values for PERIOD:");

                        System.out.println("CLASSROOM ID: ");
                        PERIOD_IDCLASSROOM = input.nextLine();

                        System.out.println("CLASS ID: ");
                        PERIOD_IDCLASS = input.nextLine();

                        System.out.println("CLASS CLASS: ");
                        PERIOD_CLASSCLASS = input.nextLine();

                        System.out.println("TEACHER ID: ");
                        PERIOD_IDTEACHER = input.nextLine();

                        System.out.println("SUBJECT NAME: ");
                        PERIOD_NAMESUBJECT = input.nextLine();

                        System.out.println("STARTING HOUR: ");
                        PERIOD_HRSTART = input.nextLine();

                        System.out.println("ENDING HOUR: ");
                        PERIOD_HREND = input.nextLine();

                        stmnt = " INSERT INTO FN72014.PERIOD(IDCLASSROOM, IDCLASS, CLASSCLASS, IDTEACHER, NAMESUBJECT, HRSTART, HREND)"
                                + " VALUES ('" + PERIOD_IDCLASSROOM + "','" + PERIOD_IDCLASS + "','" + PERIOD_CLASSCLASS + "','" +
                                PERIOD_IDTEACHER + "','" + PERIOD_NAMESUBJECT + "','" + PERIOD_HRSTART + "','" + PERIOD_HREND + "')";
                        db2Obj.insert(stmnt);
                    }
                }

            }

            if (choice.equals("DELETE")) {
                System.out.println("Choose table to delete from:");
                choice = input.nextLine();

                switch (choice) {
                    case "CLASSROOM" -> {
                        System.out.println("Enter ID to DELETE");
                        CLASSROOM_ID = input.nextLine();

                        stmnt = "DELETE FROM FN72014.CLASSROOM WHERE ID = " + "'" + CLASSROOM_ID + "' ";
                    }
                    case "SUBJECT" -> {
                        System.out.println("Enter NAME to DELETE");
                        SUBJECT_NAME = input.nextLine();

                        stmnt = "DELETE FROM FN72014.SUBJECT WHERE NAME = " + "'" + SUBJECT_NAME + "' ";
                    }
                    case "TEACHER" -> {
                        System.out.println("Enter ID to DELETE");
                        TEACHER_ID = input.nextLine();

                        stmnt = "DELETE FROM FN72014.TEACHER WHERE ID = " + "'" + TEACHER_ID + "' ";
                    }
                    case "CLASS" -> {
                        System.out.println("Enter ID and CLASS to DELETE");
                        CLASS_ID = input.nextLine();
                        CLASS_CLASS = input.nextLine();

                        stmnt = "DELETE FROM FN72014.CLASS WHERE ID = " + "'" + CLASS_ID + "' AND CLASS = " + "'" + CLASS_CLASS + "' ";
                    }
                    case "PERIOD" -> {
                        System.out.println("Enter CLASSROOM ID to DELETE");
                        PERIOD_IDCLASSROOM = input.nextLine();

                        stmnt = "DELETE FROM FN72014.PERIOD WHERE IDCLASSROOM = " + "'" + PERIOD_IDCLASSROOM + "' ";
                    }
                }


                    db2Obj.delete(stmnt);
            }
        }
            db2Obj.closeConnection();
    }
}
