import java.util.ArrayList;

public class Main {
    // Función recursiva para identificar y contar secuencias de genes en una cadena de ADN
    public static int contarSecuenciasGenes(String adn, int inicio) {
        int count = 0;
        int index = adn.indexOf("ATG", inicio); // Buscar la marca "ATG" a partir de la posición 'inicio'

        // Si se encuentra la marca "ATG"
        while (index != -1) {
            // Buscar la siguiente marca "ATG" después de la posición actual de la marca "ATG"
            int end = buscarFinGen(adn, index + 3);
            if (end != -1) {
                count++; // Incrementar el contador de secuencias de genes encontradas
                // Buscar la siguiente marca "ATG" después del final del gen actual
                index = adn.indexOf("ATG", end);
            } else {
                break;
            }
        }
        return count;
    }

    // Función auxiliar para encontrar el final de un gen
    private static int buscarFinGen(String adn, int inicio) {
        int indexTAA = adn.indexOf("TAA", inicio);
        int indexTAG = adn.indexOf("TAG", inicio);
        int indexTGA = adn.indexOf("TGA", inicio);

        // Encontrar el mínimo de los índices (el que ocurra primero)
        int minIndex = Math.min(indexTAA == -1 ? Integer.MAX_VALUE : indexTAA,
                Math.min(indexTAG == -1 ? Integer.MAX_VALUE : indexTAG,
                        indexTGA == -1 ? Integer.MAX_VALUE : indexTGA));

        // Si se encontró algún índice, devolverlo; de lo contrario, devolver -1
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }

    // Función recursiva para calcular combinaciones genéticas basadas en un modelo simplificado
    public static ArrayList<String> calcularCombinacionesGeneticas(String genes, int longitud, int nivel) {
        ArrayList<String> combinaciones = new ArrayList<>();

        // Caso base: si la longitud deseada es 0 o si el nivel es mayor que la longitud del gen
        if (longitud == 0 || nivel >= genes.length()) {
            combinaciones.add("");
            return combinaciones;
        }

        // Recursión: calcular las combinaciones incluyendo y excluyendo el gen en el nivel actual
        ArrayList<String> combinacionesExcluyendo = calcularCombinacionesGeneticas(genes, longitud, nivel + 1);
        ArrayList<String> combinacionesIncluyendo = calcularCombinacionesGeneticas(genes, longitud - 1, nivel + 1);

        // Agregar las combinaciones excluyendo el gen actual
        combinaciones.addAll(combinacionesExcluyendo);

        // Agregar las combinaciones incluyendo el gen actual
        for (String combinacion : combinacionesIncluyendo) {
            combinaciones.add(genes.charAt(nivel) + combinacion);
        }

        return combinaciones;
    }

    public static void main(String[] args) {
        String adn = "ATGCATGCTAAATGCTAGGCTGA"; // Ejemplo de cadena de ADN
        String genes = "ACGT"; // Modelado de genes simplificado

        // Conteo de secuencias de genes en la cadena de ADN
        int conteoGenes = contarSecuenciasGenes(adn, 0);
        System.out.println("Número de secuencias de genes encontradas: " + conteoGenes);

        // Cálculo de combinaciones genéticas
        ArrayList<String> combinaciones = calcularCombinacionesGeneticas(genes, 3, 0);
        System.out.println("Combinaciones genéticas posibles:");
        for (String combinacion : combinaciones) {
            System.out.println(combinacion);
        }
    }
}
