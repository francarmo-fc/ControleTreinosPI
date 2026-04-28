import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistroTreinoDAO {

    private Connection conn;

    public RegistroTreinoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(RegistroTreino r) {

        try {

            String sql = "INSERT INTO registro_treino "
                    + "(usuario_id, treino_id, data, observacao) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, r.getUsuarioId());
            stmt.setInt(2, r.getTreinoId());
            stmt.setString(3, r.getData());
            stmt.setString(4, r.getObservacao());

            stmt.execute();
            stmt.close();

        } catch (Exception e) {

            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<RegistroTreino> listar() {

        List<RegistroTreino> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM registro_treino";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                RegistroTreino r = new RegistroTreino();

                r.setId(rs.getInt("id"));
                r.setUsuarioId(rs.getInt("usuario_id"));
                r.setTreinoId(rs.getInt("treino_id"));
                r.setData(rs.getString("data"));
                r.setObservacao(rs.getString("observacao"));

                lista.add(r);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {

            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }
}