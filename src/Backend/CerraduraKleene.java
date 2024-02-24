package Backend;

import java.util.Scanner;

public class CerraduraKleene {

    private String[] palabras;
    private int nivelCerradura;

    public CerraduraKleene(String[] palabras, int nivelCerradura) {
        this.palabras = palabras;
        this.nivelCerradura = nivelCerradura;
    }
    
    public static void main(String[] args) {
        String[] palabras;
        int nivelCerradura;
        Scanner input = new Scanner(System.in);

        System.out.println("Cuantas palabras contiene el lenguaje: ");
        int cantidadPalabras = input.nextInt();
        System.out.println("Ingresa las " + cantidadPalabras + " palabras de lenguaje:");

        palabras = new String[cantidadPalabras];
        for (int i = 0; i < cantidadPalabras; i++) {
            palabras[i] = input.next();
        }

        System.out.println("Ingrese el nivel de cerradura de Kleene que se desea conocer: ");
        nivelCerradura = input.nextInt();

        CerraduraKleene calcular = new CerraduraKleene(palabras, nivelCerradura);

        String[] resultado = calcular.palabrasGeneradas();

        System.out.println("\nPalabras formadas:");
        System.out.println(String.join("-", resultado));
        calcular.mostrarCantidadPalabras();
    }

    public String[] palabrasGeneradas() {
        if (nivelCerradura == 0) {
            return new String[]{"λ"};
        }

        int totalPalabras = (int) Math.pow(palabras.length, nivelCerradura);
        String[] palabrasGeneradas = new String[totalPalabras];

        for (int i = 0; i < totalPalabras; i++) {
            StringBuilder palabraActual = new StringBuilder();
            int tempIndex = i;

            for (int j = nivelCerradura - 1; j >= 0; j--) {
                int palabraIndex = tempIndex % palabras.length;
                palabraActual.insert(0, palabras[palabraIndex]);
                tempIndex /= palabras.length;
            }
            palabrasGeneradas[i] = palabraActual.toString();
        }
        return palabrasGeneradas;
    }

    private void mostrarCantidadPalabras() {
        System.out.println("Cantidad de palabras: " + (int) Math.pow(palabras.length, nivelCerradura));
    }

}
