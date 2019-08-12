package controlador;

import com.google.gson.Gson;
import modelo.Categoria;
import modelo.Libro;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="adminCategoria", urlPatterns = {"/categoria"})
public class AdminCategoria extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String jsonString = null;
        try {
            jsonString = request.getParameter("categoria");
        }catch(Exception e){
            e.printStackTrace();
        }
        String json = "null";
        response.setContentType("application/json");
        if (jsonString!=null){
            Categoria categoria = null;
            try{
                categoria = new Gson().fromJson(jsonString, Categoria.class);
            }catch(Exception e){
                e.printStackTrace();
                categoria = null;
            }
            if (categoria!=null){

                if (categoria.insertar()){
                    json = "{\"error\":200,\"mensaje\":\"ok\"}";
                }else{
                    json = "{\"error\":500,\"mensaje\":\"Error en la insercion\"}";
                }

            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
            }

        }else{
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String search;
        try{
            search = request.getParameter("search");
        }catch(Exception e){
            e.printStackTrace();
            search = "";
        }

        Categoria categoria = null;
        String json = null;
        if (search!=null && search.equals("name")){
            categoria = new Categoria();
            String nombre;
            try{
                nombre = request.getParameter("name");
            }catch(Exception e){
                e.printStackTrace();
                nombre = null;
            }

            if (nombre!=null){
                categoria.setCategoria(nombre);
                if (categoria.consultarXNombre()){
                    json = new Gson().toJson(categoria);
                }else{
                    json = "{\"error\":500,\"mensaje\":\"No existe\"}";
                }
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
            }

        }else{
            categoria = new Categoria();
            List<Categoria> categorias = categoria.consultasAll();
            if (categorias!=null){
                json = new Gson().toJson(categorias);
            }else{

                json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
            }
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json);

    }

}
