package src.main.java.com.test.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mahasiswa {
    String nama;
    String nim;
    String programStudi;
    String fakultas;
    String universitas;

    // Constructor
    public Mahasiswa(String nama, String nim, String programStudi, String fakultas, String universitas) {
        this.nama = nama;
        this.nim = nim;
        this.programStudi = programStudi;
        this.fakultas = fakultas;
        this.universitas = universitas;
    }

    // Method untuk menampilkan data
    public void printData() {
        System.out.println("Nama: " + this.nama);
        System.out.println("NIM: " + this.nim);
        System.out.println("Program Studi: " + this.programStudi);
        System.out.println("Fakultas: " + this.fakultas);
        System.out.println("Universitas: " + this.universitas);
    }

    // Method untuk menyimpan data mahasiswa ke dalam database
    public void saveToDatabase() {
        // Koneksi ke database
        String url = "jdbc:mysql://localhost:3306/test"; // Ubah sesuai dengan nama database kamu
        String username = "root"; // Ganti dengan username MySQL kamu
        String password = "Aji123"; // Ganti dengan password MySQL kamu

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Membuat koneksi
            conn = DriverManager.getConnection(url, username, password);

            // Query untuk memasukkan data ke tabel mahasiswa
            String sql = "INSERT INTO mahasiswa (nama, nim, program_studi, fakultas, universitas) VALUES (?, ?, ?, ?, ?)";

            // Menggunakan PreparedStatement untuk menghindari SQL Injection
            stmt = conn.prepareStatement(sql);

            // Mengisi nilai parameter di query
            stmt.setString(1, this.nama);
            stmt.setString(2, this.nim);
            stmt.setString(3, this.programStudi);
            stmt.setString(4, this.fakultas);
            stmt.setString(5, this.universitas);

            // Menjalankan query
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data mahasiswa berhasil ditambahkan ke database.");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menambahkan data ke database.");
            e.printStackTrace();
        } finally {
            // Menutup koneksi dan statement
            try {
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
