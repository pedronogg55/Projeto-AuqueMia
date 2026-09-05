package auquemia.DAO;

import auquemia.entidades.Medicamento;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MedicamentoDAO {

    private BancoDeDados banco = new BancoDeDados();
    private Connection conexao;

    private void validar(Medicamento m) {
        if (m.getNome() == null || m.getNome().isBlank()) {
            throw new RuntimeException("Informe o nome do medicamento.");
        }

        if (m.getFormato() == null || m.getFormato().isBlank()) {
            throw new RuntimeException("Informe se o medicamento é injetável ou em comprimido.");
        }

        if (m.getFabricante() == null || m.getFabricante().isBlank()) {
            throw new RuntimeException("Informe o fabricante.");
        }

        if (m.getDataVencimento() == null || m.getDataVencimento().isBlank()) {
            throw new RuntimeException("Informe a data de validade do medicamento.");
        }
    }

    public void criar(Medicamento m) {
        validar(m);
        
        String sql = "INSERT INTO medicamento(idconsulta, nome, formato, fabricante, data_vencimento) VALUES (?,?,?,?,?);";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(m.getIdConsulta());
        parametros.add(m.getNome());
        parametros.add(m.getFormato());
        parametros.add(m.getFabricante());
        parametros.add(m.getDataVencimento());

        banco.inserirConexao(sql, parametros);
    }

    public List<Medicamento> ler() {
        String sql = "SELECT * FROM medicamento;";
        List<Medicamento> lista = new ArrayList<>();
        conexao = banco.conectar();

        try {
            ResultSet resultset = banco.executarQuery(sql);
            while (resultset.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setId(resultset.getInt("idmedicamento"));
                medicamento.setIdConsulta(resultset.getInt("idconsulta"));
                medicamento.setNome(resultset.getString("nome"));
                medicamento.setFormato(resultset.getString("formato"));
                medicamento.setFabricante(resultset.getString("fabricante"));
                medicamento.setDataVencimento(resultset.getString("data_vencimento"));

                lista.add(medicamento);
            }
        } catch (SQLException exce) {
            System.out.println("Erro de conexão.");
            exce.printStackTrace();
        } finally {
            banco.desconectar();
        }

        return lista;
    }

    public void atualizar(Medicamento m) {
        validar(m);
        String sql = "UPDATE medicamento SET idconsulta = ?, nome = ?, formato = ?, fabricante = ?, data_vencimento = ? WHERE idmedicamento = ?;";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(m.getIdConsulta());
        parametros.add(m.getNome());
        parametros.add(m.getFormato());
        parametros.add(m.getFabricante());
        parametros.add(m.getDataVencimento());
        parametros.add(m.getId());

        banco.inserirConexao(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM medicamento USERS WHERE idmedicamento = ?;";
        List parametros = new ArrayList<>();

        parametros.add(id);

        banco.inserirConexao(sql, parametros);
    }
}
