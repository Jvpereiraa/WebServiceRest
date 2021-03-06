
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Usuario;
import ws.CORSFilter;

/**
 * REST Web Service
 *
 * @author zelphy
 */
@Path("generic")
public class FornecedorWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FornecedorWS
     */
    public FornecedorWS() {
    }

    /**
     * Retrieves representation of an instance of ws.FornecedorWS
     * @return an instance of java.lang.String
     */
  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usuario/get/{id}")
    public String getUsuario(@PathParam("id") Integer id){
       Usuario u = new Usuario();
       u.setId(id);
       UsuarioDAO dao = new UsuarioDAO();
       u = dao.buscar(u);
       //Convertendo para  gson
       Gson g = new Gson();
        
       return g.toJson(u);
    }
    
   @DELETE
   //@Produces(MediaType.TEXT_PLAIN)
   //@Consumes(MediaType.TEXT_PLAIN)
   @Path("Usuario/delete/{id}")
   public boolean deleteUsuario(@PathParam("id") Integer id){
       Usuario u = new Usuario();
       u.setId(id);
       UsuarioDAO dao = new UsuarioDAO();
       u =  dao.buscar(u);
       return dao.excluir(u);
   }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Usuario/inserir")
    public boolean inserirUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserir(u);         
    
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Usuario/alterar")
    public boolean alterarUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.atualizar(u);
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    @Path("Usuario/list")
    public String listUsuario(){
        
        
   
       List<Usuario> lista; 
       
       
       UsuarioDAO dao = new UsuarioDAO();
       lista = dao.listar();
       
       Gson g = new Gson();
        
       return g.toJson(lista);
    }
    
    

}


