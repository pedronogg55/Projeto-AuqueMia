package auquemia.DAO;

import auquemia.entidades.Animal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AnimalDAO {

    private BancoDeDados banco = new BancoDeDados();
    private Connection conexao;

    private void validar(Animal a) {
        if (a.getNome() == null || a.getNome().isBlank()) {
            throw new RuntimeException("Por favor, informe o nome do animal.");
        }

        if (a.getEspecie() == null || a.getEspecie().isBlank()) {
            throw new RuntimeException("Informe a espécie do animal.");
        }
        
        if (a.getRaca() == null || a.getRaca().isBlank()) {
            throw new RuntimeException("Informe se o animal possui raça ou é SRD.");
        }

        if (a.getIdade() == null || a.getIdade().isBlank()) {
            throw new RuntimeException("Informe a idade do animal (Em extenso ou número)");
        }
    }

    public void criar(Animal a) {
        validar(a);
        String sql = "INSERT INTO animal(idtutor, nome, especie, raca, idade) VALUES (?, ?, ?, ?, ?)";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(a.getIdTutor());
        parametros.add(a.getNome());
        parametros.add(a.getEspecie());
        parametros.add(a.getRaca());
        parametros.add(a.getIdade());

        banco.inserirConexao(sql, parametros);
    }

    public List<Animal> ler() {
        String sql = "SELECT * FROM animal;";
        List<Animal> lista = new ArrayList<>();

        conexao = banco.conectar();

        try {
            ResultSet resultset = banco.executarQuery(sql);
            while (resultset.next()) {
                Animal animal = new Animal();

                animal.setId(resultset.getInt("idanimal"));
                animal.setIdTutor(resultset.getInt("idtutor"));
                animal.setNome(resultset.getString("nome"));
                animal.setEspecie(resultset.getString("especie"));
                animal.setRaca(resultset.getString("raca"));
                animal.setIdade(resultset.getString("idade"));

                lista.add(animal);
            }

        } catch (SQLException exce) {
            System.out.println("Erro de conexão.");
            exce.printStackTrace();
        } finally {
            banco.desconectar();
        }
        return lista;
    }

    public void atualizar(Animal a) {
        validar(a);
        
        String sql = "UPDATE animal SET idtutor = ?, nome = ?, especie = ?, raca = ?, idade = ? WHERE idanimal = ?;";
        conexao = banco.conectar();
        List parametros = new ArrayList<>();

        parametros.add(a.getIdTutor());
        parametros.add(a.getNome());
        parametros.add(a.getEspecie());
        parametros.add(a.getRaca());
        parametros.add(a.getIdade());
        parametros.add(a.getId());

        banco.inserirConexao(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM animal USERS WHERE idanimal = ?;";
        List parametros = new ArrayList<>();

        parametros.add(id);

        banco.inserirConexao(sql, parametros);
    }
}
