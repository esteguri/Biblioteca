package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int id;
    private String nombre;
    private String autor;
    private int idCategoria;
    private String estante;

    //'{"id":12,"nombre":"Cien","autor":"Yo","idCategoria":4,"estante":"A"}'

    public Libro() {
    }

    public Libro(int id) {
        this.id = id;
    }

    public Libro(int id, String nombre, String autor, int idCategoria, String estante) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.estante = estante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", idCategoria=" + idCategoria +
                ", estante='" + estante + '\'' +
                '}';
    }

    //Consultas que devuelven listas
    public List<Libro> consultasAll(String SELECT){
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Libro> libros = new ArrayList<Libro>();
                while (rs.next()){
                    Libro producto = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                    libros.add(producto);
                }
                oConexion.desconectar();
                return libros;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean consultar(){
        String SELECT = "SELECT * FROM `libro` WHERE `id`= "+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.nombre = rs.getString(2);
                    this.autor = rs.getString(3);
                    this.idCategoria = rs.getInt(4);
                    this.autor = rs.getString(5);
                    oConexion.desconectar();
                    return true;
                }else{
                    oConexion.desconectar();
                    return false;
                }
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean consultarXNombre(){
        String SELECT = "SELECT * FROM libro WHERE `nombre`= '"+this.nombre+"' " ;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.id = rs.getInt(1);
                    this.autor = rs.getString(2);
                    this.idCategoria = rs.getInt(3);
                    this.autor = rs.getString(4);
                    this.estante = rs.getString(5);
                    oConexion.desconectar();
                    return true;
                }else{
                    oConexion.desconectar();
                    return false;
                }
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return false;
            }
        }
        return false;
    }


    public List<Libro> consultarXCategoria(){
        String SELECT = "SELECT * FROM libro WHERE idCategoria="+this.idCategoria;
        return consultasAll(SELECT);
    }

    public List<Libro> consultarTabla(){
        String SELECT = "SELECT * FROM libro";
        return consultasAll(SELECT);
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO `libro`(`nombre`, `autor`,`idCategoria`,`estante`) VALUES('"+this.nombre+"','"+this.autor+"',"+this.idCategoria+",'"+this.estante+"')";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }


}
