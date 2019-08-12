package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private int id;
    private String categoria;

    public Categoria() {
    }

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO `categoria`(`categoria`) VALUES ('"+this.categoria+"')";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public boolean consultarXNombre(){
        String SELECT = "SELECT * FROM categoria WHERE `categoria`= '"+this.categoria+"' " ;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.id = rs.getInt(1);
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

        public List<Categoria> consultasAll(){
        String SELECT ="SELECT * FROM categoria";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Categoria> categorias = new ArrayList<Categoria>();
                while (rs.next()){
                    Categoria categoria  = new Categoria(rs.getInt(1), rs.getString(2));
                    categorias.add(categoria);
                }
                oConexion.desconectar();
                return categorias;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}
