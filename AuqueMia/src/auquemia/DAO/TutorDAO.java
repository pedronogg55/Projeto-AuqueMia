package auquemia.DAO;

import auquemia.entidades.Tutor;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TutorDAO {

    private BancoDeDados banco = new BancoDeDados();
    private Connection conexao;

    private void validar(Tutor t) {

        if (t.getNome() == null || t.getNome().isBlank()) {
            throw new RuntimeException("Por favor, preencha o campo nome.");
        }

        if (t.getCpf() == null || t.getCpf().isBlank()) {
            throw new RuntimeException("Por favor, insira um CPF de 11 números.");
        }

        if (!t.getCpf().matches("\\d{11}")) {
            throw new RuntimeException("O CPF deve conter apenas 11 números.");
        }

        if (t.getTelefone() == null || t.getTelefone().isBlank()) {
            throw new RuntimeException("Por favor, informe um telefone de contato.");
        }

        if (t.getEndereco() == null || t.getEndereco().isBlank()) {
            throw new RuntimeException("Informe um local de residência.");
        }
    }

    public void criar(Tutor t) {
        validar(t);
        
        String sql = "INSERT INTO tutor(nome, cpf, telefone, endereco) VALUES (?,?,?,?);";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(t.getNome());
        parametros.add(t.getCpf());
        parametros.add(t.getTelefone());
        parametros.add(t.getEndereco());

        banco.inserirConexao(sql, parametros);
    }

    public List<Tutor> ler() {
        String sql = "SELECT * FROM tutor;";
        List<Tutor> lista = new ArrayList<>();
        conexao = banco.conectar();

        try {
            ResultSet resultset = banco.executarQuery(sql);
            while (resultset.next()) {
                Tutor tutor = new Tutor();
                tutor.setId(resultset.getInt("idtutor"));
                tutor.setNome(resultset.getString("nome"));
                tutor.setCpf(resultset.getString("cpf"));
                tutor.setTelefone(resultset.getString("telefone"));
                tutor.setEndereco(resultset.getString("endereco"));

                lista.add(tutor);
            }
        } catch (SQLException exce) {
            System.out.println("Erro de conexão.");
            exce.printStackTrace();
        } finally {
            banco.desconectar();
        }
        return lista;
    }

    public void atualizar(Tutor t) {
        validar(t);
        
        String sql = "UPDATE tutor SET nome = ?, cpf = ?, telefone = ?, endereco = ? WHERE idtutor = ?;";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(t.getNome());
        parametros.add(t.getCpf());
        parametros.add(t.getTelefone());
        parametros.add(t.getEndereco());
        parametros.add(t.getId());

        banco.inserirConexao(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tutor USERS WHERE idtutor = ?;";
        List parametros = new ArrayList<>();

        parametros.add(id);

        banco.inserirConexao(sql, parametros);
    }
}
