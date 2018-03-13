
package consumindowebserv;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import modelo.Usuario;

/**
 *
 * @author marcelosiedler
 */
public class HttpExemplo {

    private final String USER_AGENT = "Mozilla/5.0";

     public static void main(String[] args) throws Exception {

        HttpExemplo http = new HttpExemplo();
        Gson g = new Gson();
        Usuario u = new Usuario();
        Type usuarioType = new TypeToken<Usuario>() {
        }.getType();
        
        /*
       // Inseri o usuario
        u.setId(9);
        u.setNome("programatche");
        u.setEmail("aaa.com");
        u.setComment("dsdasd");
        u.setCnpj("5465");
        String json = g.toJson(u, usuarioType);
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/inserir";
        http.sendPost(url, json,"POST");
        */
        
        
        
        //Atualiza o usuario
        
        u.setId(8);
        u.setNome("matheus");
        u.setEmail("aaa.com");
        u.setComment("dsdasd");
        u.setCnpj("5465");
        String json = g.toJson(u, usuarioType);
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/alterar";
        http.sendPost(url, json,"PUT");
        
        
        /*
        //Pegando um usuário
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/get/3";
        String json = http.sendGet(url,"GET");
        u = g.fromJson(json, usuarioType);
        System.out.println(u.getNome());

        */
       
        /*
        //Lista todos os usuarios
       
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/list";
        String json = http.sendGet(url,"GET");      
        System.out.println(json);
        */
       
        /*
        //Deleta usuario
        String url = "http://localhost:8080/Fornecedor/webresources/generic/Usuario/delete/6";
        String retorno = http.sendGet(url,"DELETE");
        System.out.println(retorno);
        */
      
       
    }

    // HTTP GET request
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
