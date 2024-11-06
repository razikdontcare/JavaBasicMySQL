package src.main.java.com.test.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Aji123";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Memuat driver MySQL (tidak selalu diperlukan mulai dari JDBC 4.0, driver
            // otomatis dimuat)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuat koneksi
            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Koneksi berhasil!");

            // Lakukan operasi database di sini...
            stmt = conn.createStatement();
            // Query SQL untuk menampilkan semua data dari tabel mahasiswa
            String sql = "SELECT * FROM mahasiswa";
            // Menjalankan query dan menyimpan hasilnya di ResultSet
            rs = stmt.executeQuery(sql);

            System.out.println("Data Mahasiswa:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");
                String programStudi = rs.getString("program_studi");
                String fakultas = rs.getString("fakultas");
                String universitas = rs.getString("universitas");

                System.out
                        .println("ID: " + id + ", Nama: " + nama + ", NIM: " + nim + ", Program Studi: " + programStudi
                                + ", Fakultas: " + fakultas + ", Universitas: " + universitas);
            }
        } catch (SQLException e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan!");
            e.printStackTrace();
        } finally {
            // Menutup ResultSet, Statement, dan Connection untuk membebaskan resource
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
