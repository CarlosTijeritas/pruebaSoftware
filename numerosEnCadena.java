import java.util.Scanner;

/**
 *
 * @author carlo
 */
public class ContarNumerosCadena {
    
    public static boolean isNumeric(String str) {
    try {
        double d = Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
        return false; 
    }
    return true; 
}
    public static void main(String[] args){
        
       int counter=0;
       while(true){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nIntroduce la cadena");
        String textoValidar=sc.nextLine();
        for (int i = 0; i < textoValidar.length(); i++) {
        if (isNumeric(String.valueOf(textoValidar.charAt(i)))){
              counter++;
        }else{
              counter+=0;
        }      
    } 
    System.out.println("La cantidad de números en el string son:" + counter);    }
}
}
