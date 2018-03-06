/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author marcelosiedler
 */
public class UsuarioDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    public UsuarioDAO()
    {
    
    }
    
    public boolean inserir(Usuario usuario)
    {
        String sql = "INSERT INTO fornecedor(id,nome,email,comentario,cnpj) VALUES(?,?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setInt(1, usuario.getId());
            pst.setString(2, usuario.getNome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getComentario());
            pst.setString(5, usuario.getCnpj());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    
    /*
    public boolean atualizar(Usuario usuario)
    {
        String sql = "UPDATE usuario set senha=?,perfil=?,email=? where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            pst.setString(1, usuario.getSenha());
            pst.setString(2, usuario.getPerfil());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getLogin());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }

        */
    public boolean excluir(Usuario usuario)
    {
        String sql = "DELETE FROM fornecedor where id=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
           
            pst.setInt(1, usuario.getId());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    
    public List<Usuario> listar()
    {
         String sql = "SELECT * FROM fornecedor";
        List<Usuario> retorno = new ArrayList<Usuario>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Usuario item = new Usuario();
                item.setId(res.getInt("id"));
                item.setNome(res.getString("nome"));
                item.setEmail(res.getString("email"));
                item.setComentario(res.getString("comentario"));
                item.setCnpj(res.getString("cnpj"));
                
                retorno.add(item);
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    public Usuario buscar(Usuario usuario)
    {
         String sql = "SELECT * FROM fornecedor where id=?";
        Usuario retorno = null;
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            pst.setInt(1, usuario.getId());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                retorno = new Usuario();
                retorno.setId(res.getInt("id"));
                retorno.setNome(res.getString("nome"));
                retorno.setEmail(res.getString("email"));
                retorno.setComentario(res.getString("comentario"));
                retorno.setCnpj(res.getString("cnpj"));
                
                
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }


}
