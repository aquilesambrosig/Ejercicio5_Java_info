import java.io.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

import javax.swing.plaf.basic.BasicComboPopup.ListDataHandler;

import java.time.format.*;

import jdk.vm.ci.meta.Local;
import jdk.vm.ci.meta.Value;


public class LectorEmpleado {
    
    public static void main(String[] args) throws IOException {
        String path = "D:/Users/Aquiles/Documents/Info etapa 3/Java/Ejercicios/Clase3-06/ejercicio.txt";
        List<Empleado> empleados = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String stringempleado = br.readLine();
                
                          
          
            
           
            while (stringempleado != null) {

               
                String[] datosempleado = stringempleado.split(",");
                /*ESTO ES PARA CAMBIAR FORMATO DE STRING A DECIMAL Y DATE*/
                
                BigDecimal sueldo = new BigDecimal(datosempleado[3]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate nac = LocalDate.parse(datosempleado[2], formatter);
                /*---------------------------------------------------*/
                empleados.add(new Empleado(datosempleado[0],datosempleado[1],nac,sueldo));
                stringempleado = br.readLine();
                
                
            }
            
            /*for (Empleado emp : empleados) {                
                   System.out.println(emp.nombre+" - "+emp.apellido+" - "+emp.nac+" - "+emp.sueldo);                                               
            }*/
          
            


        }
        List<Empleado> empiezaCon = filtroEmpleados(empleados, "A");
        for (Empleado emp : empiezaCon) {
            System.out.println(emp.nombre+" - "+emp.apellido+" - "+emp.nac+" - "+emp.sueldo);

            
        }
        masJovenYViejo(empleados);
        mayorYMenorSueldo(empleados);
       
    }
/*
.
.
.
.
.
.
.
.
.
.
.
.
.
.
*/


public static List<Empleado> filtroEmpleados(List<Empleado> listaDeEmpeleados, String letraComienzo) {
    List<Empleado> list= new ArrayList<>();

    for (Empleado emp : listaDeEmpeleados) {
        if (emp.apellido.startsWith(letraComienzo)) {
            list.add(emp);
                      
        }
        
    }
    
   return list;
   
    
}
 public static void masJovenYViejo(List<Empleado> listaDeEmpleados) {
    /*List<Empleado> list= new ArrayList<>();*/
     
     Empleado masJoven = listaDeEmpleados
      .stream()
      .min(Comparator.comparing(Empleado::getEdad))
      .orElseThrow(NoSuchElementException::new);

      Empleado masViejo = listaDeEmpleados
      .stream()
      .max(Comparator.comparing(Empleado::getEdad))
      .orElseThrow(NoSuchElementException::new);

      System.out.println(masViejo.nombre+" - "+masViejo.getEdad());
     System.out.println(masJoven.nombre+" - "+masJoven.getEdad());


    
    /*list.add(masJoven);
    list.add(masViejo);
    return list*/
    

    
 }

 public static void mayorYMenorSueldo(List<Empleado> listaDeEmpleados) {
     Empleado mayorSueldo = listaDeEmpleados.stream().max(Comparator.comparing(Empleado::getSueldo)).orElseThrow(NoSuchElementException::new);
     Empleado menorSueldo = listaDeEmpleados.stream().min(Comparator.comparing(Empleado::getSueldo)).orElseThrow(NoSuchElementException::new);

     System.out.println(mayorSueldo.nombre+" - "+mayorSueldo.getSueldo());
     System.out.println(menorSueldo.nombre+" - "+menorSueldo.getSueldo());
     
 }

    
}


class Empleado {
    LocalDate nac;
    String nombre;
    String apellido;
    BigDecimal sueldo;
 

    public Empleado( String nombre, String apellido, LocalDate nac, BigDecimal sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nac = nac;
        this.sueldo = sueldo;
      
        
    }
    public int getEdad() {
        int edad = LocalDate.now().getYear() - this.nac.getYear();
        return edad;
    }

    public BigDecimal getSueldo() {
        BigDecimal sueldo = this.sueldo;
        return sueldo;
    }
}
