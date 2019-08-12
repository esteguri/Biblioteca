package controlador;

import com.google.gson.Gson;
import modelo.Libro;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminLibro", urlPatterns = {"/libro"})
public class AdminLibro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String jsonString = null;
        try {
            jsonString = request.getParameter("libro");
        }catch(Exception e){
            e.printStackTrace();
        }
        String json = "null";
        response.setContentType("application/json");
        if (jsonString!=null){
            Libro libro = null;
            try{
                libro = new Gson().fromJson(jsonString, Libro.class);
            }catch(Exception e){
                e.printStackTrace();
                libro = null;
            }
            if (libro!=null){

                if (libro.insertar()){
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
            search = null;
        }

        Libro libro = null;
        String json = null;
        if (search.equals("name")){
            libro = new Libro();
            String nombre;
            try{
                nombre = request.getParameter("name");
            }catch(Exception e){
                e.printStackTrace();
                nombre = null;
            }

            if (nombre!=null){
                libro.setNombre(nombre);
                if (libro.consultarXNombre()){
                    json = new Gson().toJson(libro);
                }else{
                    json = "{\"error\":500,\"mensaje\":\"No existe\"}";
                }
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
            }

        }else if(search.equals("categoria")){
            libro = new Libro();
            int categoria;
            try{
                categoria = Integer.parseInt(request.getParameter("categoria"));
            }catch(Exception e){
                e.printStackTrace();
                categoria = -1;
            }

            if (categoria!=-1){
                libro.setIdCategoria(categoria);
                List<Libro> libros = libro.consultarXCategoria();
                if (libros!=null){
                    json = new Gson().toJson(libros);
                }else{
                    json = "{\"error\":500,\"mensaje\":\"No existe\"}";
                }
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
            }
        }else{
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json);

    }







}
