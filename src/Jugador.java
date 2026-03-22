import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MARGEN_IZQUIERDA = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN_IZQUIERDA + DISTANCIA * (TOTAL_CARTAS - 1);
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN_SUPERIOR);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        String resultado = "";
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                resultado += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";

            }
        }

        return resultado.isEmpty() ? "No se encontraron grupos" : "Se encontraron los siguientes grupos:\n" + resultado;
    }
    
    public String getEscaleras() {
        String resultado = "";

        for (Pinta p : Pinta.values()) {
            boolean valores[] = new boolean[NombreCarta.values().length];

            for (Carta carta : cartas)
                if (carta.getPinta() == p)
                    valores[carta.getNombre().ordinal()] = true;
            
        
            int contador = 0;
            int inicio = -1;
            int fin = -1;

            for (int i = 0; i <= valores.length; i++) {
                if (i < valores.length && valores[i]) {
                    if (contador == 0)
                        inicio = i;

                    contador++;
                    fin = i;
                } else {
                    if (contador >= 2)
                        resultado += Grupo.values()[contador] + 
                        " en escalera de " + p +
                        " de " + NombreCarta.values()[inicio] +
                        " a " + NombreCarta.values()[fin] + "\n";

                    contador = 0;
                }
            }
    
        }
        return resultado.isEmpty() ? "No se encontraron escaleras" : resultado;
    }



    public int getPuntaje() {
    int puntaje = 0;

    int[] contadores = new int[NombreCarta.values().length];

    
    for (Carta carta : cartas) {
        contadores[carta.getNombre().ordinal()]++;
    }

    
    for (Carta carta : cartas) {

        NombreCarta nombre = carta.getNombre();
        int cantidad = contadores[nombre.ordinal()];

        
        if (cantidad < 2) {

            if (nombre == NombreCarta.AS ||
                nombre == NombreCarta.JACK ||
                nombre == NombreCarta.QUEEN ||
                nombre == NombreCarta.KING) {

                puntaje += 10;

            } else {
                puntaje += nombre.ordinal() + 1;
            }
        }
    }
    return puntaje;
}
}