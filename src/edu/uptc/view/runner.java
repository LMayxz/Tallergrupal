package edu.uptc.view;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
                        spaceLines();
                        break;
                    case '4':
                        numberEgo();
                        break;
                    case '5':
                        magic_numbers();
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
    
	// 1. NumerosRomanos
	
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

    // 2. FactoresPrimos
    
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

	// 3. Espacios	

 	public static void spaceLines() {
		
		String text = JOptionPane.showInputDialog("Ingrese la cadena de texto");
        String operation = DeleteSpaces(text);
        JOptionPane.showMessageDialog(null, operation);
	}
	
    public static String DeleteSpaces(String text) {
	        text = text.trim();
	        Pattern pattern = Pattern.compile("\\s+");
	        Matcher matcher = pattern.matcher(text);
	        text = matcher.replaceAll(" ");
	        return text;
	    }

	// 4. NumeroEgolatra

	public static void numberEgo() {
		try {
            String numString = JOptionPane.showInputDialog("Ingrese el número que desee");
            
            int numero = Integer.parseInt(numString);
            
            if (esEgolatra(numero)) {
                String resultado = numero + " es un número ególatra.";
                JOptionPane.showMessageDialog(null, resultado);
            } else {
                String resultado = numero + " no es un número ególatra.";
                JOptionPane.showMessageDialog(null, resultado);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada no válida. Debe ingresar un número entero.");
        }
		}
	
	public static boolean esEgolatra(int numero) {
		String numeroStr = String.valueOf(numero);
		int n = numeroStr.length();
		int suma = 0;
		for (int i = 0; i < n; i++) {
			int digito = Character.getNumericValue(numeroStr.charAt(i));
		    suma += Math.pow(digito, n);
		    }
		return suma == numero;
		}

// 5. NumerosMagicos
	 private static void magic_numbers(){
		 magic();
	 }
	 
	 public static void magic() {
        try {
            String input = JOptionPane.showInputDialog("Ingrese un número:");
            int number = Integer.parseInt(input);
            
            if (isMagicNumber(number)) {
                JOptionPane.showMessageDialog(null, number + " es un número mágico.");
            } else {
                JOptionPane.showMessageDialog(null, number + " no es un número mágico.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada no válida. Debe ingresar un número entero.");
        }
    }
    
    public static boolean isMagicNumber(int number) {
        String numberString = Integer.toString(number);
        String sortedAscending = sortDigitsAscending(numberString);
        String sortedDescending = sortDigitsDescending(numberString);
        
        int result = Integer.parseInt(sortedDescending) - Integer.parseInt(sortedAscending);
        
        return result == number;
    }
    
    public static String sortDigitsAscending(String numberString) {
        char[] digits = numberString.toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }
    
    public static String sortDigitsDescending(String numberString) {
        char[] digits = numberString.toCharArray();
        Arrays.sort(digits);
        return new StringBuilder(new String(digits)).reverse().toString();
    }

	// 6. Fecha
    
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
