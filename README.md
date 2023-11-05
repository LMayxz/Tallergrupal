# Tallergrupal 
package edu.uptc.view;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		mainMenu();
	}
    
	// Menu
	
	private static void mainMenu() {
        String menu = "<<<<<<<MENU PRINCIPAL>>>>>>>\n"
        		+ "\n 1. Numero Romano"
                + "\n 2. Factores Primos"
                + "\n 3. Borrar Espacios"
                + "\n 4. Números Ególatras"
                + "\n 5. Número Mágico"
                + "\n 6. Fechas"
                + "\n Para salir presione enter sin ingresar ningun valor.";
        char option = 0;
        do {
            try {
                String input = JOptionPane.showInputDialog(menu);
                if (input == null || input.isEmpty()) {
                    break;
                }
                
                option = input.charAt(0);
                
                switch (option) {
                    case '1':
                        roman();
                        break;
                    case '2':
                        prime();
                        break;
                    case '3':
                        // Realizar acciones para la opción 3
                        break;
                    case '4':
                        // Realizar acciones para la opción 4
                        break;
                    case '5':
                        // Realizar acciones para la opción 5
                        break;
                    case '6':
                        fecha();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                }
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Intente nuevamente.");
            }
        } while (true);
    }
    
	// NumerosRomanos
	
    private static void roman() {

        try {
        	String romanNumber = JOptionPane.showInputDialog("Digite el numero romano que desea convertir");
        	int decimalNumber = convertRomanToDecimal(romanNumber);
        	JOptionPane.showMessageDialog(null, romanNumber + " en decimal es: " + decimalNumber);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        
    }
   
    public static int convertRomanToDecimal(String romanNumber) {
        romanNumber = romanNumber.toUpperCase();
        
        int decimalNumber = 0;
        
        for (int i = 0; i < romanNumber.length(); i++) {
            char currentChar = romanNumber.charAt(i);
            
            if (!isValidRomanChar(currentChar)) {
                throw new IllegalArgumentException("Carácter romano inválido: " + currentChar);
            }
            
            int currentValue = getRomanValue(currentChar);
            
            if (i + 1 < romanNumber.length()) {
                char nextChar = romanNumber.charAt(i + 1);
                
                if (!isValidRomanChar(nextChar)) {
                    throw new IllegalArgumentException("Carácter romano inválido: " + nextChar);
                }
                
                int nextValue = getRomanValue(nextChar);
                
                if (currentValue < nextValue) {
                    decimalNumber -= currentValue;
                } else {
                    decimalNumber += currentValue;
                }
            } else {
                decimalNumber += currentValue;
            }
        }
        
        return decimalNumber;
    }
    
    public static boolean isValidRomanChar(char romanChar) {
        return romanChar == 'I' || romanChar == 'V' || romanChar == 'X' ||
               romanChar == 'L' || romanChar == 'C' || romanChar == 'D' || romanChar == 'M';
    }
    
    public static int getRomanValue(char romanChar) {
        switch (romanChar) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("Carácter romano inválido: " + romanChar);
        }
    }

    // FactoresPrimos
    
    public static void prime() {
    	 boolean isValidInput = false;
         
         while (!isValidInput) {
             try {
                 String input = JOptionPane.showInputDialog("Ingrese un número:");
                 
                 if (input == null) {
                     break;
                 }
                 
                 int number = Integer.parseInt(input);
                 
                 List<Integer> primeFactors = findPrimeFactors(number);
                 String result = formatPrimeFactors(primeFactors);
                 
                 String output = "Los factores primos de " + number + " son: " + primeFactors + "\n" +
                                 "Expresado en formato de potencias: " + result;
                 
                 JOptionPane.showMessageDialog(null, output);
                 
                 isValidInput = true;
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Error: Entrada no válida. Debe ingresar un número entero.");
             }
         }
    }
    
    public static List<Integer> findPrimeFactors(int number) {
        List<Integer> primeFactors = new ArrayList<>();
        
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                primeFactors.add(i);
                number /= i;
            }
        }
        
        return primeFactors;
    }
    
    public static String formatPrimeFactors(List<Integer> primeFactors) {
        StringBuilder result = new StringBuilder();
        
        int currentFactor = primeFactors.get(0);
        int count = 1;
        
        for (int i = 1; i < primeFactors.size(); i++) {
            int factor = primeFactors.get(i);
            
            if (factor == currentFactor) {
                count++;
            } else {
                result.append(currentFactor).append("^").append(count).append(" * ");
                currentFactor = factor;
                count = 1;
            }
        }
        
        result.append(currentFactor).append("^").append(count);
        
        return result.toString();
    }


	private static void fecha(){
		  Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una fecha (dd/mm/aaaa): ");
        String fechaTexto = scanner.nextLine();
        
        String descripcionFecha = obtenerDescripcionFecha(fechaTexto);
        
        if (descripcionFecha != null) {
            System.out.println("Descripción de la fecha: " + descripcionFecha);
        } else {
            System.out.println("La fecha ingresada no corresponde a una fecha válida.");
        }
	}


	
        public static String obtenerDescripcionFecha(String fechaTexto) {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        
        try {
            Date fecha = formatoFecha.parse(fechaTexto);
            
            DateFormat formatoDescripcion = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
            return formatoDescripcion.format(fecha);
        } catch (ParseException e) {
            return null;
        }
    }
}
