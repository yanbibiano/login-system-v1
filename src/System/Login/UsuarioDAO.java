package System.Login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastroUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) values (?, ?, ?)";

        String senhaMascarada = gerarHashSHA256(senha);

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senhaMascarada);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário no banco: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        String senhaMascarada = gerarHashSHA256(senha);

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senhaMascarada);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar login no banco: " + e.getMessage());
            return false;
        }
    }
    private String gerarHashSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errp Crítico: Algoritimo de segurança não encontrado");
        }
    }
}
