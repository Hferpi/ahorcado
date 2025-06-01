package logica;

// BaseDatos.java
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseDatos {
    // Configuración para MySQL - URL corregida
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AhorcadoMySQL";
    private static final String USER = "root";
    private static final String PASSWORD = "abcd1234";

    public static void inicializarBD() {
        try {
            // Cargar explícitamente el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                // Create users table if it doesn't exist - MySQL syntax
                String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "nombre VARCHAR(50) NOT NULL UNIQUE," +
                        "contrasena VARCHAR(100) NOT NULL," +
                        "puntuacion INT DEFAULT 0," +
                        "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

                stmt.execute(sql);
                System.out.println("Base de datos inicializada correctamente");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Registrar un nuevo usuario
    public static boolean registrarUsuario(String nombre, String contrasena) {
        // Comprobar si el usuario ya existe
        if (existeUsuario(nombre)) {
            return false;
        }

        String sql = "INSERT INTO usuarios (nombre, contrasena) VALUES (?, ?)";

        try {
            // Cargar explícitamente el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombre);
                pstmt.setString(2, contrasena); // Nota: en una aplicación real, deberías encriptar la contraseña

                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // Verificar login
    public static boolean verificarLogin(String nombre, String contrasena) {
        String sql = "SELECT contrasena FROM usuarios WHERE nombre = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombre);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String contrasenaAlmacenada = rs.getString("contrasena");
                        return contrasenaAlmacenada.equals(contrasena);
                    }
                }

                return false;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Error al verificar login: " + e.getMessage());
            return false;
        }
    }

    // Comprobar si un usuario existe
    public static boolean existeUsuario(String nombre) {
        String sql = "SELECT nombre FROM usuarios WHERE nombre = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombre);

                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Error al comprobar usuario: " + e.getMessage());
            return false;
        }
    }

    // Actualizar puntuación
    public static boolean actualizarPuntuacion(String nombre, int puntos) {
        String sql = "UPDATE usuarios SET puntuacion = puntuacion + ? WHERE nombre = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, puntos);
                pstmt.setString(2, nombre);

                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("Error al actualizar puntuación: " + e.getMessage());
            return false;
        }
    }

    // Obtener puntuación
    public static int obtenerPuntuacion(String nombre) {
        String sql = "SELECT puntuacion FROM usuarios WHERE nombre = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombre);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("puntuacion");
                    }
                }

                return 0;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
            return 0;
        } catch (SQLException e) {
            System.err.println("Error al obtener puntuación: " + e.getMessage());
            return 0;
        }
    }

    public static List<Map<String, Object>> obtenerRanking() {
        String sql = "SELECT nombre, puntuacion, fecha_registro FROM usuarios ORDER BY puntuacion DESC";
        List<Map<String, Object>> ranking = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Map<String, Object> jugador = new HashMap<>();
                    jugador.put("nombre", rs.getString("nombre"));
                    jugador.put("puntuacion", rs.getInt("puntuacion"));
                    jugador.put("fecha_registro", rs.getString("fecha_registro"));
                    ranking.add(jugador);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al obtener ranking: " + e.getMessage());
        }

        return ranking;
    }

    // Obtener contraseña de un usuario (para la función de recuperar contraseña)
    public static String obtenerContrasena(String nombre) {
        String sql = "SELECT contrasena FROM usuarios WHERE nombre = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombre);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("contrasena");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al recuperar contraseña: " + e.getMessage());
        }

        return null;
    }
}