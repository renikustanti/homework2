import java.sql.*;


public class ConnectToDatabase {
    public static void main(String[] args) {
        String userName = "digitalskola";
        String password = "digitalskola";
        String connectionString = "jdbc:postgresql://127.0.0.1:5432/etl_db?user="+userName+"&password="+password;

        String createTableQuery = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        
        String deleteQuery = "DELETE FROM REGISTRATION WHERE id = 101";
        String selectQuery = "SELECT * FROM REGISTRATION";



        try {
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmnt = conn.createStatement();

            stmnt.executeUpdate(deleteQuery);

            ResultSet rs = stmnt.executeQuery(selectQuery);

            while (rs.next()) {
                System.out.println("======================================");
                System.out.println("Kode Provinsi: " + rs.getInt("kode"));
                System.out.println("Nama Provinsi: " + rs.getString("nama"));
                System.out.println("Tingkat Pendidikan: " + rs.getString("tingkat"));
                System.out.println("Jenis Kelamin: " + rs.getInt("jenis"));
                System.out.println("Jumlah individu: " + rs.getInt("jumlah"));
            }


            stmnt.close();
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
