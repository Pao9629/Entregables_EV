import java.util.*;
import javax.swing.*

;
//Comienzo importando la clase util porque englobará util.Random y Scanner (necesarios para el desarrollo de las funciones) y más
// siendo en el punto 5.5 cuando debemos meter una funcion de coordenada aleatoria


// Esta es la cclase principal que contiene la lógica del juego
public class VerJOptionPane_EnfonsarLaFlota {


//==> METODO MAIN <==== metodo principal que iniciará el juego
    
public static void main(String[] args) {
   
    JOptionPane.showMessageDialog(null,"==== -By Pao - Aprobado por Chayanne ¡¡POR FIN FUNCIONA!! Juego: HUNDIR LA FLOTA =====");
        JOptionPane.showMessageDialog(null,"Niveles de dificultad:\n1. Nivel fácil: 5 lanchas, 3 barcos, 1 acorazado y un portaaviones. (50 intentos) \n");

        // Lee la opción escogida por el usuario
        String opcionEscogida = JOptionPane.showInputDialog(null, "¿Qué nivel escoges? (Ingresa el número de la opción)");
        int nivel = Integer.parseInt(opcionEscogida);

        // Variables para almacenar datos según la elección del usuario
        int lanchas = 0, barcos = 0, acorazado = 0, portaaviones = 0, intentos = 0;

        // Configura las opciones según la elección del usuario
        switch (nivel) {
            case 1:
                lanchas = 5;
                barcos = 3;
                acorazado = 1;
                portaaviones = 1;
                intentos = 50;
                break;
        
            case 2:
                lanchas = 2;
                barcos = 1;
                acorazado = 1;
                portaaviones = 1;
                intentos = 30;
                break;

            case 3:
                lanchas = 1;
                barcos = 1;
                intentos = 10;
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción incorrecta");
                return;
        }

        // Comienza el juego con las opciones seleccionadas
        jugarPartida(10, 10, lanchas, barcos, acorazado, portaaviones, intentos);

        // Cierra el Scanner (Si lo has usado) al finalizar el juego
        // scanner.close();
    }


//=====> METODO PEDIR DATO <========

        //En las instrucciones se nos indica el uso de la llamada de la función anteriormente creada
        // "PEDIR_DATOS_ENTRE_MAXYMIN" para simplificar el proceso de validación de los datos introducidos
  

// Este método valida la entrada del usuario entre un rango mínimo y máximo usando JOptionPane
public static int pedirDatoEntreMaxYMin(int min, int max) {
    int opcion = -1;
    boolean entradaValida = false;

    while (!entradaValida) {
        try {
            String userInput = JOptionPane.showInputDialog(null, "Introduzca un número entre " + min + " y " + max + ":");
            opcion = Integer.parseInt(userInput);

            if (opcion < min || opcion > max) {
                JOptionPane.showMessageDialog(null, "Opción fuera de rango, elige de nuevo");
            } else {
                entradaValida = true;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Introduce un número válido.");
        }
    }
    return opcion;
}


//=======> METODO JUGAR PARTIDA <=========

        //Empecemos con la creación del método JUGAR_PARTIDA para jugar la partida con las instrucciones dadas

        public static void jugarPartida(int tamanoX, int tamanoY, int lanchas, int barcos, int acorazado, int portaaviones, int intentos) {
            JOptionPane.showMessageDialog(null, "¡Comencemos la partida, buena suerte!");
        
            char[][] tableroJugador = crearTablero(tamanoX, tamanoY);
            char[][] tableroOrdenador = crearTablero(tamanoX, tamanoY);
        
            insertarBarcosAleatorio(tableroOrdenador, lanchas, barcos, acorazado, portaaviones);
        
            while (intentos > 0 && quedanBarcosSinHundir(tableroOrdenador)) {
                JOptionPane.showMessageDialog(null, "Intentos restantes: " + intentos);
                mostrarTablero(tableroJugador, false);
        
                int[] coordenadas = pedirCoordenadas();
                procesarTiro(tableroOrdenador, tableroJugador, coordenadas);
        
                intentos--;
            }
        
            // Resto del código permanece igual...
            // Asegúrate de actualizar las salidas de consola a JOptionPane.showMessageDialog() y adaptar las entradas de usuario.
        }
        // Con esta iteración se me permite ver cuantos intentos me quedan

//=========> METODOS TABLERO<=========

            // Aquí tuve un problema de visualización que explicaré en la memoria documental 
            //** FUNCIÓN MOSTRAR TABLERO 
            //Vamos primero con MOSTRAR TABLERO, usaremos bucle if else



        //** FUNCIÓN MOSTRAR TABLERO <==== 

        // Ahora sigamos con MOSTRAR TABLERO (habrá dos formas de mostrarse), usaremos bucle for e if y el uso de char y booleanos.
        // Mostrará el tablero con opciones true o false, con true muestra el contenido completo del tauler
        // Con false mostrara solo los disparos X y A y ocultará la psociión de los barcos

        public static char[][] crearTablero(int tamanoX, int tamanoY) {
            char[][] tablero = new char[tamanoX][tamanoY];
            for (int i = 0; i < tamanoX; i++) {
                for (int j = 0; j < tamanoY; j++) {
                    tablero[i][j] = '-';
                }
            }
            return tablero;
        }
        




public static void mostrarTablero(char[][] tablero, boolean visualizacionCompleta) {
    StringBuilder display = new StringBuilder();
    display.append("   ");
    
    for (int i = 1; i <= tablero.length; i++) {
        display.append(i).append(" ");
    }
    
    display.append("\n  ---------------------\n");
    
    for (int i = 0; i < tablero.length; i++) {
        if (i < 9) {
            display.append((i + 1)).append(" | ");
        } else {
            display.append((i + 1)).append("| ");
        }
        
        for (int j = 0; j < tablero[i].length; j++) {
            char contenido = '-';
            if (visualizacionCompleta) {
                contenido = tablero[i][j];
            } else {
                if (tablero[i][j] == 'X') {
                    contenido = 'X'; 
                } else if (tablero[i][j] == 'A') {
                    contenido = 'A'; 
                }
            }
            display.append(contenido).append(" ");
        }
        display.append("\n");
    }
    display.append("  ---------------------");
    
    JOptionPane.showMessageDialog(null, display.toString());
}

        

//=======> FUNCIÓN COORDENADA ALEATORIA (insertaré las coordinadas y los barcos) <======= 
        
        //Vamos con la función de COORDENADA ALEATORIA, aquí usaremos el método random

        public static void insertarBarcosAleatorio(char[][] tablero, int lanchas, int barcos, int acorazado, int portaaviones) {
            insertarBarco(tablero, 'L', lanchas, 1);
            insertarBarco(tablero, 'B', barcos, 3);
            insertarBarco(tablero, 'Z', acorazado, 4);
            insertarBarco(tablero, 'P', portaaviones, 5);
        }
    
        private static void insertarBarco(char[][] tablero, char tipoBarco, int cantidad, int tamaño) {
            Random random = new Random();
            int intentosMaximos = 100;
            int tamañoFilas = tablero.length;
            int tamañoColumnas = tablero[0].length;
    
            for (int i = 0; i < cantidad; i++) {
                int intentos = 0;
                boolean barcoInsertado = false;
    
                while (intentos < intentosMaximos && !barcoInsertado) {
                    int fila = random.nextInt(tamañoFilas);
                    int columna = random.nextInt(tamañoColumnas - tamaño + 1);
    
                    if (validarInsercion(tablero, fila, columna, tamaño)) {
                        insertarBarcoEnTablero(tablero, tipoBarco, fila, columna, tamaño);
                        barcoInsertado = true;
                    }
    
                    intentos++;
                }
    
                if (!barcoInsertado) {
                    JOptionPane.showMessageDialog(null, "ERROR: No se pudo insertar el barco de tipo " + tipoBarco);
                }
            }
        }

        //Validamos las inserciones metidas por el jugador
        private static boolean validarInsercion(char[][] tablero, int fila, int columna, int tamaño) {
            int tamañoColumnas = tablero[0].length;
        
            if (columna + tamaño > tamañoColumnas) {
                JOptionPane.showMessageDialog(null, "El barco se sale del tablero", "Error de inserción", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        
            for (int i = columna; i < columna + tamaño; i++) {
                if (tablero[fila][i] != '-') {
                    JOptionPane.showMessageDialog(null, "Ya hay un barco en esa posición", "Error de inserción", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        
            return true;
        }
        
        private static void insertarBarcoEnTablero(char[][] tablero, char tipoBarco, int fila, int columna, int tamaño) {
            for (int i = columna; i < columna + tamaño; i++) {
                tablero[fila][i] = tipoBarco;
            }
            JOptionPane.showMessageDialog(null, "Barco insertado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }


////==> MEOTOD DE SALIDA(SURTIDA/TRET) "PEDIMOS COORDENADAS" (desarrollo las funciones para los tiros) <====  

public static int[] pedirCoordenadas() {
    int[] coordenadas = new int[2]; // Creo un array para almacenar las coordenadas [fila y columna].

    coordenadas[0] = Integer.parseInt(JOptionPane.showInputDialog("Introduce la fila del tiro (número): "));
    coordenadas[1] = Integer.parseInt(JOptionPane.showInputDialog("Introduce la columna del tiro (número): "));

    return coordenadas;
}

// Función para procesar el tiro y mostrar mensajes usando JOptionPane
public static void procesarTiro(char[][] tableroOrdenador, char[][] tableroJugador, int[] coordenadas) {
    int fila = coordenadas[0];
    int columna = coordenadas[1];

    if (fila < 0 || fila >= tableroOrdenador.length || columna < 0 || columna >= tableroOrdenador[0].length) {
        JOptionPane.showMessageDialog(null, "Las coordenadas introducidas no son válidas. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    char contenido = tableroOrdenador[fila][columna];

    JOptionPane.showMessageDialog(null, "Contenido en esa casilla: " + contenido);

    if (tableroJugador[fila][columna] == 'X' || tableroJugador[fila][columna] == 'A') {
        JOptionPane.showMessageDialog(null, "¡Error! Ya has hecho un tiro en esta casilla.", "Error", JOptionPane.ERROR_MESSAGE);
    } else if (contenido == '-') {
        JOptionPane.showMessageDialog(null, "¡Agua!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        tableroJugador[fila][columna] = 'A';
    } else {
        JOptionPane.showMessageDialog(null, "¡Tocado!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        tableroJugador[fila][columna] = 'X';
    }
}

public static boolean quedanBarcosSinHundir(char[][] tablero) {
    for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
            if (tablero[i][j] != '-' && tablero[i][j] != 'X' && tablero[i][j] != 'A') {
                return true;
            }
        }
    }
    return false;
}


//Ahora en primer lugar se muestra el tablero completo con la disposición de los barcos y los tiros realizados por el jugador.
public static void finDePartida(char[][] tableroJugador, char[][] tableroOrdenador) {
    StringBuilder tableroCompleto = new StringBuilder("Tablero completo con barcos y tiros:\n");
    tableroCompleto.append(obtenerRepresentacionTablero(tableroJugador, true));

    if (quedanBarcosSinHundir(tableroOrdenador)) {
        JOptionPane.showMessageDialog(null, tableroCompleto.toString() + "\n¡Has perdido! Aún quedan barcos enemigos.", "Fin de Partida", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, tableroCompleto.toString() + "\n¡Has ganado! Has hundido todos los barcos enemigos.", "Fin de Partida", JOptionPane.INFORMATION_MESSAGE);
    }

    JOptionPane.showMessageDialog(null, "¡Gracias por jugar!", "Fin de Partida", JOptionPane.INFORMATION_MESSAGE);
}

// Método auxiliar para obtener representación de tablero como String
public static String obtenerRepresentacionTablero(char[][] tablero, boolean visualizacionCompleta) {
    StringBuilder representacion = new StringBuilder();
    representacion.append("   ");
    for (int i = 1; i <= tablero.length; i++) {
        representacion.append(i).append(" ");
    }
    representacion.append("\n  ---------------------\n");

    for (int i = 0; i < tablero.length; i++) {
        if (i < 9) {
            representacion.append(i + 1).append(" | ");
        } else {
            representacion.append(i + 1).append("| ");
        }

        for (int j = 0; j < tablero[i].length; j++) {
            char contenido = '-';
            if (visualizacionCompleta) {
                contenido = tablero[i][j];
            } else {
                if (tablero[i][j] == 'X' || tablero[i][j] == 'A') {
                    contenido = tablero[i][j];
                }
            }
            representacion.append(contenido).append(" ");
        }
        representacion.append("\n");
    }
    representacion.append("  ---------------------\n");
    return representacion.toString();
}
}


