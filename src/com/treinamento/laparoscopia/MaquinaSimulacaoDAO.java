package com.treinamento.laparoscopia.dao;

import com.treinamento.laparoscopia.models.MaquinasSimulacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaquinasSimulacaoDAO {
    private Connection conexao;

    public MaquinasSimulacaoDAO() {
        conexao = Conexao.getConnection();
    }

    public List<MaquinasSimulacao> listarMaquinas() throws SQLException {
        List<MaquinasSimulacao> maquinas = new ArrayList<>();
        String sql = "SELECT * FROM maquina_simulacao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MaquinaSimulacao maquina = new MaquinaSimulacao();
                maquina.setId(rs.getInt("id"));
                maquina.setNome(rs.getString("nome"));
                maquina.setModelo(rs.getString("modelo"));
                maquinas.add(maquina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao listar máquinas de simulação.");
        }
        return maquinas;
    }
}
