package System.Login;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciadorDeArquivo {

    private static final String NOME_ARQUIVO = "bando_de_dados.txt";

    public void salvasUtilizadores(ArrayList<Usuario> listaDeUsuarios) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {

            for (Usuario u : listaDeUsuarios) {

                String tipo = (u instanceof Administrador) ? "ADMIN" : "COMUM";

                String linha = u.getNome() + ";" + u.getEmail() + ";" + u.getSenha() + ";" + tipo;

                escritor.write(linha);
                escritor.newLine();
            }
            System.out.println("backup automático realizado no HD!");

        } catch (IOException e) {
            System.out.println("Erro crítico: Não foi possível salvar no disco ");
        }
    }

    public ArrayList<Usuario> carregarUtilizadores() {
        ArrayList<Usuario> listaCarregada = new ArrayList<>();
        java.io.File arquivo = new java.io.File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return listaCarregada;
        }

        try (java.io.BufferedReader leitor = new java.io.BufferedReader(new java.io.FileReader(arquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length <4) {
                    continue;
                }

                String nome = partes[0];
                String email = partes[1];
                String senha = partes[2];
                String tipo = partes[3];

                if (tipo.equals("ADMIN")) {
                    listaCarregada.add(new Administrador(nome, email, senha));
                } else {
                    listaCarregada.add(new UsuarioComum(nome, email, senha));
                }
            }

            System.out.println("Memoria recuperada " + listaCarregada.size() + " utilizador(es) carregado(s) do HD." );
        } catch (IOException e) {
            System.out.println("Erro ao recuperar dados do HD: " + e.getMessage());
        }
        return listaCarregada;
    }

}
