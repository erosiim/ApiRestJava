package org.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conection {
    //Variable de instancia única
    private static Conection c;
    //Variable para hacer la conexión
    private Connection cx = null;
    private Conection() {
        
        try {
            Class.forName("org.postgresql.Driver");
            cx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webservice",
                    "postgres", "12334");
            System.out.println("-Conectado-");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public static Conection getInstance(){
        if(c == null)
            c = new Conection();
        return c;
    }
    
    //Statement(Sentencias SQL):  Insert, Delete, Update => De actualización
    public boolean execute(String sql){
        boolean res = false;
        try {
            Statement st = cx.createStatement();
            st.execute(sql);
            res = true;
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
         return res;
    }
    //Statement(Sentencia SQL): Select => De consulta
    //ResultSet: almacena de forma temporal el resultado de la consulta Select y permite recorrer la información obtenida.
    public ResultSet executeQuery(String sql){
        ResultSet res= null;        
        try {
            Statement st = cx.createStatement();
            res = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
         return res;
    }
        public List executeThisQuery(String sql) throws SQLException{
        ResultSet res= null;
        Statement st = cx.createStatement();
        List<Empleado> lst = new ArrayList();
        res = st.executeQuery(sql);
        while(res.next()){
            Empleado p = new Empleado();
            p.setClave(res.getString(1));
            p.setNombre(res.getString(2));
            p.setDireccion(res.getString(3));
            p.setTelefono(res.getString(4));
            lst.add(p);
        }
//        System.out.println(lst.get(0).getClave() + lst.get(0).getNombre() + lst.get(0).getDireccion() + lst.get(0).getTelefono());
//        System.out.println(lst.get(1).getClave() + lst.get(1).getNombre() + lst.get(1).getDireccion() + lst.get(1).getTelefono());
        return lst;
    }  

}

