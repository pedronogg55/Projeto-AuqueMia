package auquemia.DAO;

import auquemia.entidades.Consulta;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConsultaDAO {

    private BancoDeDados banco = new BancoDeDados();
    private Connection conexao;
    
    private void validar(Consulta c){
        if (c.getMotivo() == null || c.getMotivo().isBlank()) {
            throw new RuntimeException("Informe o motivo da consulta");
        }

        if (c.getTemperatura() == 0.0) {
            throw new RuntimeException("Informe a temperatura do animal");
        }
        
        if (c.getStatus() == null || c.getStatus().isBlank()) {
            throw new RuntimeException("Informe se a consulta está em andamento ou finalizada.");
        }
    }

    public void criar(Consulta c) {
        validar(c);
        
        String sql = "INSERT INTO consulta(idveterinario, idanimal, data, motivo, temperatura, diagnostico, status, valor) VALUES (?,?,now(),?,?,?,?,?);";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();
        
        parametros.add(c.getIdVeterinario());
        parametros.add(c.getIdAnimal());
        parametros.add(c.getMotivo());
        parametros.add(c.getTemperatura());
        parametros.add(c.getDiagnostico());
        parametros.add(c.getStatus());
        parametros.add(c.getValor());

        banco.inserirConexao(sql, parametros);
    }

    public List<Consulta> ler() {
        String sql = "SELECT * FROM consulta;";
        List<Consulta> lista = new ArrayList<>();
        conexao = banco.conectar();

        try {
            ResultSet resultset = banco.executarQuery(sql);
            while (resultset.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(resultset.getInt("idconsulta"));
                consulta.setIdVeterinario(resultset.getInt("idveterinario"));
                consulta.setIdAnimal(resultset.getInt("idanimal"));
                consulta.setData(resultset.getTimestamp("data"));
                consulta.setMotivo(resultset.getString("motivo"));
                consulta.setTemperatura(resultset.getDouble("temperatura"));
                consulta.setDiagnostico(resultset.getString("diagnostico"));
                consulta.setStatus(resultset.getString("status"));
                consulta.setValor(resultset.getDouble("valor"));

                lista.add(consulta);
            }
        } catch (SQLException exce) {
            System.out.println("Erro de conexão.");
            exce.printStackTrace();
        } finally {
            banco.desconectar();
        }

        return lista;
    }

    public void atualizar(Consulta c) {
        validar(c);
        
        String sql = "UPDATE consulta SET idveterinario = ?, idanimal = ?, data = now(), motivo = ?, temperatura = ?, diagnostico = ?, status = ?, valor = ? WHERE idconsulta = ?;";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(c.getIdVeterinario());
        parametros.add(c.getIdAnimal());
        parametros.add(c.getMotivo());
        parametros.add(c.getTemperatura());
        parametros.add(c.getDiagnostico());
        parametros.add(c.getStatus());
        parametros.add(c.getValor());
        parametros.add(c.getId());

        banco.inserirConexao(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM consulta USERS WHERE idconsulta = ?;";
        List parametros = new ArrayList<>();
        parametros.add(id);

        banco.inserirConexao(sql, parametros);
    }
}
