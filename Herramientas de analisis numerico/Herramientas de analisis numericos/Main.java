public class Main {

    public static int sumatoria(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sumatoria(n - 1);
        }
    }

    public static void listarNumeros(int desde, int hasta) {
        if (desde <= hasta) {
            System.out.print(desde + " ");
            listarNumeros(desde + 1, hasta);
        }
    }

    public static int potencia(int base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else {
            return base * potencia(base, exponente - 1);
        }
    }

    public static int encontrarMaximo(int[] datos, int n) {
        if (n == 1) {
            return datos[0];
        } else {
            int maximoRestante = encontrarMaximo(datos, n - 1);
            return datos[n - 1] > maximoRestante ? datos[n - 1] : maximoRestante;
        }
    }

    public static void main(String[] args) {

        System.out.println("Sumatoria de los primeros 5 números naturales: " + sumatoria(5));
        System.out.print("Listado de números del 1 al 10: ");
        listarNumeros(1, 10);
        System.out.println("\nPotencia de 2^3: " + potencia(2, 3));
        int[] datos = {3, 7, 2, 9, 5};
        System.out.println("Valor máximo en el conjunto [3, 7, 2, 9, 5]: " + encontrarMaximo(datos, datos.length));
    }
}
