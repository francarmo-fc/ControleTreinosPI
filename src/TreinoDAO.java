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

public class TreinoDAO {

    private Connection conn;

    public TreinoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Treino t) {

        try {

            String sql = "INSERT INTO treino "
                    + "(nome, descricao, duracao) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getDescricao());
            stmt.setInt(3, t.getDuracao());

            stmt.execute();
            stmt.close();

        } catch (Exception e) {

            System.out.println("Erro ao inserir treino: " + e.getMessage());
        }
    }

    public List<Treino> listar() {

        List<Treino> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM treino";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Treino t = new Treino();

                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setDescricao(rs.getString("descricao"));
                t.setDuracao(rs.getInt("duracao"));

                lista.add(t);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {

            System.out.println("Erro ao listar treinos: " + e.getMessage());
        }

        return lista;
    }
}