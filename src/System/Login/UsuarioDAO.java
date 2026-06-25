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

    public String buscarNomePorEmail (String email) {
        String sql = "SELECT nome FROM usuarios WHERE email = ?";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar nome no banco: " + e.getMessage());
        }
        return "Usuário";
    }

    public boolean alterarSenha(String email, String novaSenha) {
        String sql = "UPDATE usuarios SET senha = ? WHERE email = ?";

        String novaSenhaMascarada = gerarHashSHA256(novaSenha);

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novaSenhaMascarada);
            stmt.setString(2, email);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar senha no banco: " + e.getMessage());
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
