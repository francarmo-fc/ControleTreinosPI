/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc_
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO {

    private Connection conn;

    public ExercicioDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Exercicio e) {

        try {

            String sql = "INSERT INTO exercicio "
                    + "(nome, descricao) "
                    + "VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getDescricao());

            stmt.execute();
            stmt.close();

        } catch (Exception ex) {

            System.out.println("Erro ao inserir exercicio: " + ex.getMessage());
        }
    }

    public List<Exercicio> listar() {

        List<Exercicio> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM exercicio";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Exercicio e = new Exercicio();

                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));

                lista.add(e);
            }

            rs.close();
            stmt.close();

        } catch (Exception ex) {

            System.out.println("Erro ao listar exercicios: " + ex.getMessage());
        }

        return lista;
    }
}