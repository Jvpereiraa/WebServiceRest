/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.lang.reflect.Type;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import junit.framework.TestCase;
import org.junit.Test;
import modelo.Usuario;



/**
 *
 * @author zelphy
 */
public class FornecedorWSTest extends TestCase{
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    
    public FornecedorWSTest() {
    }
         
    @Test
    public void testGetUsuario() throws Exception {
        FornecedorWSTest http = new FornecedorWSTest();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/3";
        String json = http.sendGet(url,"GET");
        u = g.fromJson(json, usuarioType);
        System.out.println(u.getNome());
        
        assertEquals("programatche", u.getNome());
  
    }
    
    
    /**
     * Test of inserirUsuario method, of class FornecedorWS.
     */
    
    @Test
    public void testInserirUsuario() throws Exception {
        FornecedorWSTest http = new FornecedorWSTest();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        //Inserindo
        u.setId(6);
        u.setNome("programatche");
        u.setEmail("aaa.com");
        u.setComment("dsdasd");
        u.setCnpj("5465");
        String json = g.toJson(u, usuarioType);
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/inserir";
        http.sendPost(url, json,"POST");
        
        //buscando
        url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/6";
        json = http.sendGet(url,"GET");
        u = g.fromJson(json, usuarioType);
       // System.out.println(u.getId() + "Oii");
        assertNotNull( u.getId());     
             
    }
    

    /**
     * Test of deleteUsuario method, of class FornecedorWS.
     */
    
    @Test
    public void testDeleteUsuario() throws Exception {
        FornecedorWSTest http = new FornecedorWSTest();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/delete/5";
        String retorno = http.sendGet(url,"DELETE");
        System.out.println(retorno);       
        assertEquals("true", retorno);
        
        
    }

    
    /**
     * Test of alterarUsuario method, of class FornecedorWS.
     */
    
    @Test
    public void testAlterarUsuario() throws Exception {
        FornecedorWSTest http = new FornecedorWSTest();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        u.setId(2);
        u.setNome("Matheus");
        u.setEmail("aaa.com");
        u.setComment("dsdasd");
        u.setCnpj("5465");
        String json = g.toJson(u, usuarioType);
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/alterar";
        http.sendPost(url, json,"PUT");
        
        //buscando
        url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/2";
        json = http.sendGet(url,"GET");
        u = g.fromJson(json, usuarioType);
       // System.out.println(u.getId() + "Oii");
        assertEquals("Matheus", u.getNome());        
        
        
    }
    
    /**
     * Test of listUsuario method, of class FornecedorWS.
     */
    
    @Test
    public void testListUsuario() throws Exception {
        FornecedorWSTest http = new FornecedorWSTest();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/list";
        String json = http.sendGet(url,"GET"); 
        assertNotNull(json);
                
        
    }
   
    
   
    
     private String sendGet(String url, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod(method);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

    // HTTP POST request
    private void sendPost(String url, String urlParameters, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
    
}
