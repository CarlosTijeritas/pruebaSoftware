/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.registrousuarios;

/**
 *
 * @author carlo
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoJDBC {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT = "INSERT INTO empleado(idEmpleado, nombre, apellidoPaterno,apellidoMaterno,fechaNacimiento) VALUES(?, ?, ?, ?,?)";
    private static final String SQL_OBTENER_EDAD = "SELECT fechaNacimiento,nombre,apellidoPaterno FROM empleado WHERE idEmpleado=?";
    private static final String SQL_SELECT_ORDEN_APELLIDO = "SELECT idEmpleado, nombre, apellidoPaterno,apellidoMaterno,fechaNacimiento FROM empleado ORDER BY apellidoPaterno";
    private static final String SQL_SELECT_ORDEN_FECHANAC = "SELECT idEmpleado, nombre, apellidoPaterno,apellidoMaterno,fechaNacimiento FROM empleado ORDER BY fechaNacimiento";


    public EmpleadoJDBC() {

    }

    public EmpleadoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Empleado> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        List<Empleado> empleados = new ArrayList<Empleado>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ORDEN_APELLIDO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellidoPaterno");
                String apellidoMaterno = rs.getString("apellidoMaterno");
                String fechaNacimiento=rs.getString("fechaNacimiento");
                 
                empleado = new Empleado();
                empleado.setIdEmpleado(idEmpleado);
                empleado.setNombre(nombre);
                empleado.setApellidoPaterno(apellidoPaterno);
                empleado.setApellidoMaterno(apellidoMaterno);
                empleado.setFechaNacimiento(fechaNacimiento);
                empleados.add(empleado);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }

        return empleados;
    }
    
    public List<Empleado> selectOrderAge() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        List<Empleado> empleados = new ArrayList<Empleado>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ORDEN_FECHANAC);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellidoPaterno");
                String apellidoMaterno = rs.getString("apellidoMaterno");
                String fechaNacimiento=rs.getString("fechaNacimiento");
                 
                empleado = new Empleado();
                empleado.setIdEmpleado(idEmpleado);
                empleado.setNombre(nombre);
                empleado.setApellidoPaterno(apellidoPaterno);
                empleado.setApellidoMaterno(apellidoMaterno);
                empleado.setFechaNacimiento(fechaNacimiento);
                empleados.add(empleado);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }

        return empleados;
    }


    public int insert(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,empleado.getIdEmpleado());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApellidoPaterno());
            stmt.setString(4, empleado.getApellidoMaterno());
            stmt.setString(5, empleado.getFechaNacimiento());
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    public String calcularEdad(int idEmpleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_OBTENER_EDAD);
            stmt.setInt(1, idEmpleado);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String fechaNacimiento = rs.getString("fechaNacimiento");
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellidoPaterno");
                
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
            LocalDate  aux= LocalDate.now();
            String fechaAFormatear=String.valueOf(aux.getYear()) + "/"+ aux.getMonthValue() + "/"+ aux.getDayOfMonth();
            LocalDate hoy=LocalDate.parse(fechaAFormatear, fmt);
            
            Period periodo = Period.between(fechaNac, hoy);
            return "El empleado "+nombre+" "+apellidoPaterno+" tiene de edad "+periodo.getYears()+ " años  " + periodo.getMonths() + "meses y "+ periodo.getDays() + " dias.";
                } return "No se encontro el id";
            
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }
             
    }
    


}

