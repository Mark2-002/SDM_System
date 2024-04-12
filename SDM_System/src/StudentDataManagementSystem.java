import java.sql.*;
import java.util.Scanner;

public class StudentDataManagementSystem {
    public static void main(String[] args) {

        //Temporary store data in variable
        String url = "jdbc:Mysql://localhost:3306/jdbc_conn";
        String username = "root";
        String password = "";


        //Always use exception handling in connection related works
        try {

            // Register the Driver class using "class.forname"
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Get connection from Driver manager
            Connection conn = DriverManager.getConnection(url, username, password);


            //for making option
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\n---------------Student Data Management System--------------\n\n");
            System.out.println("Choose the option to perform action \n 1:Display \n 2:Inserted \n 3:Update \n 4:Delete \n 5:Exit");
            int choose = sc.nextInt();


            switch (choose) {
                case 1: {

                    //Display data

                    //Create Statement
                    Statement smt = conn.createStatement();

                    //finally execute sql query with the created statement and store in result set variable
                    ResultSet result = smt.executeQuery("select * from student");

                    //finally Display the data from Database
                    System.out.println("\n");
                    while (result.next()) {

                        System.out.println("\t" + result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t" + result.getString(4));
                    }
                    System.out.println("\n\n\n");

                    new Thread(() -> main(args)).start();
                    break;

                }
                case 2: {

                    // For Inserting data
                    try {
                    System.out.println("------Enter the data what you want to Insert !------");
                    System.out.println("Name:- ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Roll no:- ");
                    int roll = sc.nextInt();
                    System.out.println("Id:-");
                    int id = sc.nextInt();
                    System.out.println("Address:-");
                    sc.nextLine();
                    String address = sc.nextLine();


                        PreparedStatement stmt = conn.prepareStatement("INSERT INTO `student` (`Name`, `Roll no`, `Id`, `Adress`) VALUES (?,?,?,?);");
                        stmt.setObject(1, name);
                        stmt.setObject(2, roll);
                        stmt.setObject(3, id);
                        stmt.setObject(4, address);
                        stmt.executeUpdate();
                        System.out.println("Record inserted Successfully");
                    }catch(Exception e){
                        System.out.println("---------------------Wrong Entry-------------------");
                        new Thread(()->main(args)).start();
                        break;
                    }
                    new Thread(() -> main(args)).start();
                    break;


                }
                case 3: {

                    // For Update the data
                    try {
                    System.out.println("--------------Enter the Id you want to update !------------\n Id:-");
                    int ID = sc.nextInt();
                    System.out.println("------Enter the data what you want to update !------");
                    System.out.println("Name:- ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Roll no:- ");
                    int roll = sc.nextInt();
                    System.out.println("Id:-");
                    int id = sc.nextInt();
                    System.out.println("Address:-");
                    sc.nextLine();
                    String address = sc.nextLine();


                        PreparedStatement stmt = conn.prepareStatement("UPDATE `student` SET `Name`=?,`Roll no`=?,`Id`=?,`Adress`=? WHERE Id=?;");

                        stmt.setObject(1, name);
                        stmt.setObject(2, roll);
                        stmt.setObject(3, id);
                        stmt.setObject(4, address);
                        stmt.setObject(5, ID);
                        stmt.executeUpdate();
                        System.out.println("------------Record Updated successfully-------------");
                    }catch(Exception e){
                        System.out.println("----------------------Wrong Entry------------------");
                        new Thread(()->main(args)).start();
                        break;
                    }
                    new Thread(() -> main(args)).start();
                    break;
                }

                case 4: {

                    // For removing data
                    System.out.println("--------------Enter the Id you want to Remove !------------\n Id:-");
                    int ID = sc.nextInt();

                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM student where Id=" + ID + " ");
                    stmt.executeUpdate();
                    System.out.println("Data Deleted Successfully");
                    new Thread(() -> main(args)).start();
                    break;
                }

                case 5: {
                    //To terminate the program
                    System.exit(0);
                    break;
                }
            }


            //for Display data


            //For update data


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("--------------Try again--------------");
            new Thread(() -> main(args)).start();

        }

    }
}