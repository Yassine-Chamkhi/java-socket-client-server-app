import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class ObjectServer {
    static String FirstName="N/A";
    static String LastName="N/A";
    static String Email="N/A";
    static String Filiere="N/A";
    static float Moyenne=0;
    static int Groupe=0;

    public static void Connect(int id) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String query = "select * from student where id=" + id;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(
                    "jdbc:derby:student_data");
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery(query);

            // 4. Process the result set

            // We need to store the info from the DB in an array wala haja hakka
            while (myRs.next()) {
                FirstName = myRs.getString("first_name");
                LastName = myRs.getString("last_name");
                Email = myRs.getString("email");
                Filiere =myRs.getString("filiere");
                Groupe = myRs.getInt("groupe");
                Moyenne = myRs.getFloat("moyenne");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }

        }
    }

    public static void main(String[] argv) throws Exception {

        ServerSocket s = new ServerSocket(5000);
        System.out.println("Server started");
        while (true) {
            Socket t = s.accept();
            System.out.println("Client Connected");
            ObjectInputStream b = new ObjectInputStream(t.getInputStream());
            int received = b.readInt();
            Connect(received);
            PrintWriter output = new PrintWriter(t.getOutputStream(), true);

            output.println(FirstName+":"+LastName+":"+Email+":"+Filiere+":"+Groupe+":"+Moyenne+"\n");

            FirstName="N/A";
            LastName="N/A";
            Email="N/A";
            Filiere="N/A";
            Groupe=0;
            Moyenne=0;


            b.close();
            output.close();
            t.close();
        }
    }

}

