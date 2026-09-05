package auquemia.DAO;

import auquemia.entidades.Veterinario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    private BancoDeDados banco = new BancoDeDados();
    private Connection conexao;
    
    public void validar(Veterinario v){
        if (v.getNome() == null || v.getNome().isBlank()) {
            throw new RuntimeException("Por favor, preencha o campo nome.");
        }

        if (v.getCpf() == null || v.getCpf().isBlank()) {
            throw new RuntimeException("Por favor, insira um CPF de 11 números.");
        }

        if (!v.getCpf().matches("\\d{11}")) {
            throw new RuntimeException("O CPF deve conter apenas 11 números.");
        }

        if (v.getTelefone() == null || v.getTelefone().isBlank()) {
            throw new RuntimeException("Por favor, informe um telefone de contato.");
        }

        if (v.getCrmv() == null || v.getCrmv().isBlank()) {
            throw new RuntimeException("Informe o CRMV do veterinario.");
        }
    }
    public void criar(Veterinario v) {
        String sql = "INSERT INTO veterinario(nome, cpf, telefone, especialidade, crmv, horas_trabalhadas) VALUES (?,?,?,?,?,?);";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(v.getNome());
        parametros.add(v.getCpf());
        parametros.add(v.getTelefone());
        parametros.add(v.getEspecialidade());
        parametros.add(v.getCrmv());
        parametros.add(v.getHorasTrabalhadas());

        banco.inserirConexao(sql, parametros);
    }

    public List<Veterinario> ler() {
        String sql = "SELECT * FROM veterinario";
        List<Veterinario> lista = new ArrayList<>();
        conexao = banco.conectar();

        try {
            ResultSet resultset = banco.executarQuery(sql);
            while (resultset.next()) {
                Veterinario vet = new Veterinario();
                vet.setId(resultset.getInt("idveterinario"));
                vet.setNome(resultset.getString("nome"));
                vet.setCpf(resultset.getString("cpf"));
                vet.setTelefone(resultset.getString("telefone"));
                vet.setEspecialidade(resultset.getString("especialidade"));
                vet.setCrmv(resultset.getString("crmv"));
                vet.setHorasTrabalhadas(resultset.getInt("horas_trabalhadas"));

                lista.add(vet);
            }
        } catch (SQLException exce) {
            System.out.println("Erro de conexão.");
            exce.printStackTrace();
        } finally {
            banco.desconectar();
        }
        return lista;
    }

    public void atualizar(Veterinario v) {
        String sql = "UPDATE veterinario SET nome = ?, cpf = ?, telefone = ?, especialidade = ?, crmv = ?, horas_trabalhadas = ? WHERE idveterinario = ?;";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(v.getNome());
        parametros.add(v.getCpf());
        parametros.add(v.getTelefone());
        parametros.add(v.getEspecialidade());
        parametros.add(v.getCrmv());
        parametros.add(v.getHorasTrabalhadas());
        parametros.add(v.getId());

        banco.inserirConexao(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM veterinario USERS WHERE idveterinario = ?;";
        List parametros = new ArrayList<>();

        parametros.add(id);

        banco.inserirConexao(sql, parametros);
    }
}
