/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.registrousuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author carlo
 */
public class Main {
    
     public static void main(String[] args) {
      
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
              int opcion;
        do{
        System.out.println("\n-----------MENU-----------");
        System.out.println("1.-Registrar Empleado");
        System.out.println("2.-Obtener la edad de un empleado");
        System.out.println("3.-Obtener la lista de empleados por orden alfabetico");
        System.out.println("4.-Obtener la lista de empleados por orden de Edad");
        System.out.println("5.-Salir");
        Scanner sc = new Scanner(System.in);
        opcion=sc.nextInt();
           EmpleadoJDBC empleadoJdbc = new EmpleadoJDBC(conexion);
           switch(opcion){
               case 1:
                 String nombre;
                 String apellidoPaterno;
                 String apellidoMaterno;
                 String fechaNacimiento;
                 int idEmpleado;
                 System.out.println("Ingresa el Id del empleado");
                 idEmpleado=sc.nextInt(); sc.nextLine();
                 System.out.println("Ingresa el nombre del empleado");
                 nombre=sc.nextLine(); 
                 System.out.println("Ingresa el apellido Paterno del empleado");
                 apellidoPaterno=sc.nextLine();  
                 System.out.println("Ingresa el apellido Materno del empleado");
                 apellidoMaterno=sc.nextLine();  
                 System.out.println("Ingresa la fecha de Nacimiento empleado");
                 fechaNacimiento=sc.nextLine();  
                 
                 Empleado nuevoEmpleado = new Empleado();
                 nuevoEmpleado.setIdEmpleado(idEmpleado);
                 nuevoEmpleado.setNombre(nombre);
                 nuevoEmpleado.setApellidoPaterno(apellidoPaterno);
                 nuevoEmpleado.setApellidoMaterno(apellidoMaterno);
                 nuevoEmpleado.setFechaNacimiento(fechaNacimiento);
                 empleadoJdbc.insert(nuevoEmpleado);
                 conexion.commit(); 
                 break;
               case 2:
                   int idBuscar;
                   sc.nextLine();
                   System.out.println("Ingresa el id del empleado");
                   idBuscar=sc.nextInt();
                   String respuesta=empleadoJdbc.calcularEdad(idBuscar);
                   System.out.println(respuesta);
                   conexion.commit();
                   break;
               case 3:
                   List<Empleado> empleados=empleadoJdbc.select();
                   for(Empleado e:empleados){
                       System.out.println(e.getApellidoPaterno() + " " + e.getApellidoMaterno() + " " + e.getNombre());
                   }
                   conexion.commit(); 
                   break;
               case 4:
                   List<Empleado> empleadosEdad=empleadoJdbc.selectOrderAge();
                   for(Empleado e:empleadosEdad){
                       System.out.println(e.getApellidoPaterno() + " " + e.getApellidoMaterno() + " " + e.getFechaNacimiento());
                   }
                   conexion.commit(); 
                   break;
               default:
                   System.out.println("Inserta una opcion valida");
                
           }
        }while(opcion!=5);
   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
           
        }
    }
    
}
