/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.ConnectionFactory;

/**
 *
 * @author Igor
 */
public class PacienteDao {
    private Connection cn;
    
    public PacienteDao() {
        this.cn = new ConnectionFactory().Connect();
    }
    
    public Paciente EfetuarLogin(String cpf, String senha) throws SQLException {
        String query = " select * from paciente where cpf=? and senha=? ";
        PreparedStatement stmt = cn.prepareStatement(query);

        stmt.setString(1, cpf);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();
        
        Paciente paciente = null;
        if (rs.next()) {
            paciente = new Paciente();
            paciente.setCpf(rs.getString("cpf"));
            paciente.setNome(rs.getString("nome"));
            paciente.setTelefone(rs.getString("telefone"));
            paciente.setEmail(rs.getString("email"));
        }
        
        stmt.close();
        return paciente;
    }
    
    public void CadastrarPaciente(Paciente paciente) throws SQLException {
        String query = " insert into paciente (nome, cpf, telefone, email, senha) values (?, ?, ?, ?, ?) ";
        PreparedStatement stmt = cn.prepareStatement(query);
        
        stmt.setString(1, paciente.getNome());
        stmt.setString(2, paciente.getCpf());
        stmt.setString(3, paciente.getTelefone());
        stmt.setString(4, paciente.getEmail());
        stmt.setString(5, paciente.getSenha());
        stmt.execute();
        stmt.close();
    }
}