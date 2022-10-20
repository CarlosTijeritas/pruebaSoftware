
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


/**
 *
 * @author carlo
 */
public class ValidarFecha{
    public static void main(String[] args){
        while(true){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nIntroduce la fecha");
        String fechaIntroducida=sc.nextLine();
        String fechaSeparada[]=fechaIntroducida.split("/");
        try{
        int dia=Integer.parseInt(fechaSeparada[0]);
        int mes=Integer.parseInt(fechaSeparada[1]);
        int anio=Integer.parseInt(fechaSeparada[2]);
        if(validarFormatoExiste(dia,mes,anio)){
              System.out.println("El formato de la fecha es correcto");
              String status;
              status=validarFechaExiste(dia,mes,anio);
              System.out.println("La fecha ingresada "+status);
        }else{
             System.out.println("Formato Incorrecto,no se puede procesar la fecha ingresada dd/mm/yyyy");
        }
        }catch(Exception ex){
            System.out.println("Formato Incorrecto,no se puede procesar la fecha ingresada dd/mm/yyyy");
        }
        }
    }
    public static int calcularDiasMaximosDelMes(int anio,int mes){
    GregorianCalendar gc = new GregorianCalendar();
    gc.set(Calendar.YEAR,anio);
    gc.set(Calendar.MONTH,mes-1); 
    return gc.getActualMaximum(Calendar.DATE);  
    }
    
    public static String validarFechaExiste(int dia,int mes, int anio){
        int diaCalculado = calcularDiasMaximosDelMes(anio, mes);
        if(dia<=diaCalculado){
            return "existe";
        }
      return "no existe";
    }
    public static boolean validarFormatoExiste(int dia,int mes, int anio){
    
    try {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        formatoFecha.parse(dia + "/" + mes + "/" + anio);
        return true;
    } catch (ParseException e) {
        return false;
    }
    }
    
}
