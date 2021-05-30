import java.sql.*;
import java.io.*;

public class BacaFile {
    public static void main(String[] args) {
        String userName = "digitalskola";
        String password = "digitalskola";
        String connectionString = "jdbc:postgresql://127.0.0.1:5432/etl_db?user="+userName+"&password="+password;

        try {
            String selectQuery = "SELECT * FROM REGISTRATION";

            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmnt = conn.createStatement();

            FileReader file = new FileReader("ddk_tingkat_pendidikan.csv");
            BufferedReader reader = new BufferedReader(file);
            String lineText = null;

            // skip header
            reader.readLine();

            //
            while ((lineText = reader.readLine()) != null) {
                String[] data = lineText.split(",");
                int id = Integer.parseInt(data[0]);
                String first = data[1];
                String last = data[2];
                int age = Integer.parseInt(data[3]);

                String query = "INSERT INTO REGISTRATION VALUES ("+kode+", '"+nama+"', '"+tingkat+"','"+jenis+"'',"+jumlah+")";

                stmnt.executeUpdate(query);

            }

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

        } catch(SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
