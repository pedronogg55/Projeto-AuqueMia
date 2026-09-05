package auquemia.DAO;

import java.sql.*;
import java.util.List;

public class BancoDeDados {
    private Connection conexao;
    
    public void inserirConexao(String sql, List parametros){
        conectar();
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i+1, parametros.get(i)); 
            }
            statement.execute();
        } catch (SQLException exce){
            System.out.println("Ocorreu erro de conexão.");
            exce.printStackTrace();
        }
        desconectar();
    }
    
    public Connection conectar(){
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/auquemiadb", "postgres", "101112");
            System.out.println("Conexão criada");
        } catch (SQLException exce){
            System.out.println("Ocorreu erro de conexão.");
            exce.printStackTrace();
        } finally {
            return conexao;
        }
        
    }
    
    public void desconectar(){
        try {
            conexao.close();
        } catch (SQLException exce){
            System.out.println("Ocorreu erro de conexão.");
            exce.printStackTrace();
        }
    }
    
    public ResultSet executarQuery(String sql) throws SQLException {
        conectar();
        return conexao.createStatement().executeQuery(sql);
    }
}
