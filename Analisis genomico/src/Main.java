import java.util.ArrayList;

public class Main {

    public static int contarSecuenciasGenes(String adn, int inicio) {
        int count = 0;
        int index = adn.indexOf("ATG", inicio);


        while (index != -1) {

            int end = buscarFinGen(adn, index + 3);
            if (end != -1) {
                count++;

                index = adn.indexOf("ATG", end);
            } else {
                break;
            }
        }
        return count;
    }


    private static int buscarFinGen(String adn, int inicio) {
        int indexTAA = adn.indexOf("TAA", inicio);
        int indexTAG = adn.indexOf("TAG", inicio);
        int indexTGA = adn.indexOf("TGA", inicio);


        int minIndex = Math.min(indexTAA == -1 ? Integer.MAX_VALUE : indexTAA,
                Math.min(indexTAG == -1 ? Integer.MAX_VALUE : indexTAG,
                        indexTGA == -1 ? Integer.MAX_VALUE : indexTGA));


        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }


    public static ArrayList<String> calcularCombinacionesGeneticas(String genes, int longitud, int nivel) {
        ArrayList<String> combinaciones = new ArrayList<>();


        if (longitud == 0 || nivel >= genes.length()) {
            combinaciones.add("");
            return combinaciones;
        }


        ArrayList<String> combinacionesExcluyendo = calcularCombinacionesGeneticas(genes, longitud, nivel + 1);
        ArrayList<String> combinacionesIncluyendo = calcularCombinacionesGeneticas(genes, longitud - 1, nivel + 1);


        combinaciones.addAll(combinacionesExcluyendo);


        for (String combinacion : combinacionesIncluyendo) {
            combinaciones.add(genes.charAt(nivel) + combinacion);
        }

        return combinaciones;
    }

    public static void main(String[] args) {
        String adn = "ATGCATGCTAAATGCTAGGCTGA";
        String genes = "ACGT";


        int conteoGenes = contarSecuenciasGenes(adn, 0);
        System.out.println("Número de secuencias de genes encontradas: " + conteoGenes);


        ArrayList<String> combinaciones = calcularCombinacionesGeneticas(genes, 3, 0);
        System.out.println("Combinaciones genéticas posibles:");
        for (String combinacion : combinaciones) {
            System.out.println(combinacion);
        }
    }
}
