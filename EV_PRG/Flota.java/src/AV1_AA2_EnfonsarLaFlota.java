import java.util.*;
//Comienzo importando la clase util porque englobará util.Random y Scanner (necesarios para el desarrollo de las funciones) y más
// siendo en el punto 5.5 cuando debemos meter una funcion de coordenada aleatoria


// Esta es la cclase principal que contiene la lógica del juego
public class AV1_AA2_EnfonsarLaFlota {


//==> METODO MAIN <==== metodo principal que iniciará el juego
    
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    //Doy al usuario la bienvenida y la posibilidad de escoger diversas opciones5
    


//==> METODO MOSTRAR MENÚ <==== Solicito que diga que nivel quiere jugar

    System.out.println("====¡BIENVENIDO/A A HUNDIR LA FLOTA!=====");
    System.out.println("Niveles de dificultad:");
    System.out.println("1. Nivel fácil: 5 lanchas, 3 barcos, 1 acorazado y un portaaviones. (50 intentos).");
    System.out.println("2. Nivel medio: 2 lanchas, 1 barco, 1 acorazado y un portaaviones. (30 intentos).");
    System.out.println("3. Nivel difícil: 1 lancha y 1 barco. (10 intentos).");
    System.out.println("¿Qué nivel escoges?");

    int opcionEscogida = pedirDatoEntreMaxYMin(scanner,1,3);

    // Invocamos a la función para pedir al usuario que ingrese su elección
    //Tendremos las siguientes variables con los intentos y los barcos y almacenaremos la información según se ejecuten las funciones
    int lanchas = 0, barcos=0, acorazado = 0, portaaviones= 0, intentos = 0;

    switch (opcionEscogida) { // Aquí mediante con un switch he configurado la elección del nivel y lo he probado no hasta los 50 intentos sino modificandolo a por ejemplo 3 intentos y viendo que
        // al terminar esos "X intentos" efectivamente el juego paraba y mostraba en consola los intentos restantes mediante iteración y fin de partida
        case 1: // Pongo los casos del switch 
            lanchas = 5;
            barcos = 3;
            acorazado = 1;
            portaaviones=1;
            intentos=50;
            break;
    
        case 2:
            lanchas = 2;
            barcos = 1;
            acorazado = 1;
            portaaviones=1;
            intentos=30;
            break;

        case 3:
            lanchas=1;
            barcos=1;
            intentos=10;
            break;
        default:
            System.out.println("Opción incorrrecta");
            return;
    }

    // Una vez que hemos declarado los posibles opciones que tieene el jugador o jugadora procedamos a empezar con el método de JUGAR PARTIDA

    jugarPartida(10,10,lanchas,barcos,acorazado,portaaviones,intentos);


    scanner.close(); // Se aplica cuando finalicemos el juego pero no consigo que deje de mostrarse en el apartado "problemas"

}

//=====> METODO PEDIR DATO <========

        //En las instrucciones se nos indica el uso de la llamada de la función anteriormente creada
        // "PEDIR_DATOS_ENTRE_MAXYMIN" para simplificar el proceso de validación de los datos introducidos
  

public static int pedirDatoEntreMaxYMin(Scanner scanner, int min, int max) {


int opcion= -1; // pongo la opción -1 porque aseguro que el bucle de validación de las opcione 
// se ejecuten al menos una vez ya qye a priori -1 no es una opción que es válida según los valores
//max y min y así aseguramos que este bucle se ejecute , lo uso de marcador básicamente
    while (opcion < min || opcion > max) {
        while (!scanner.hasNextInt()) {
            System.out.println("Introduzca un número válido");
            scanner.next();
        }
        opcion = scanner.nextInt();
        if (opcion < min || opcion > max ) {
            System.out.println("Opción fuera de rango, elige de nuevo");
            
        }
    }
    return opcion;
}

//=======> METODO JUGAR PARTIDA <=========

        //Empecemos con la creación del método JUGAR_PARTIDA para jugar la partida con las instrucciones dadas

public static void jugarPartida(int tamanoX, int tamanoY, int lanchas, int barcos, int acorazado,int portaaviones, int intentos) {

System.out.println("¡Comencemos la partida, buena suerte!");

        // Usaremos aquí el char array para los tableros, el tamaño lo definiré en la creación del tablero
        //recordemos que hemos definido el tamalo en 10x10 

char[][] tableroJugador = crearTablero(tamanoX, tamanoY);
char[][] tableroOrdenador = crearTablero(tamanoX, tamanoY);

insertarBarcosAleatorio(tableroOrdenador, lanchas, barcos, acorazado, portaaviones);

//Con este bucle el jugador hace los tiros hasta quedarse sin los mismos y se va indicando en la consola 
// los intentos restantes según va colocandolos 
while (intentos > 0 && quedanBarcosSinHundir(tableroOrdenador)) {
    System.out.println("Intentos restantes: " + intentos);
    mostrarTablero(tableroJugador, false); // Se muestra el tablero del jugador 
    int[] coordenadas = pedirCoordenadas();
    procesarTiro(tableroOrdenador, tableroJugador, coordenadas); // con esto se hace el proceso del tiro que hace el jugador 
    intentos--; // Obviamente por cada iteracion se va restando el numero de intentos 
    
} // Con esta iteración se me permite ver cuantos intentos me quedan

//=========> METODOS TABLERO<=========

            // Aquí tuve un problema de visualización que explicaré en la memoria documental 
            //** FUNCIÓN MOSTRAR TABLERO 
            //Vamos primero con MOSTRAR TABLERO, usaremos bucle if else

mostrarTablero(tableroJugador, true); // Añado true. Muestra tablero al final con todos los barcos
    if (quedanBarcosSinHundir(tableroOrdenador)) {
   
        System.out.println("Has perdido, aún quedan barcos enemigos en el tablero del PC");

   
    }else{
    
        System.out.println("¡Has ganado! Has podido hundir todos los barcos enemigos DEL Pc");
}
    
}

        //** FUNCIÓN CREAR TABLERO <====
        //Ahora sigamos con CREAR TABLERO, usaremos bucle for y el uso del char para array

public static char[][] crearTablero(int tamanoX, int tamanoY) {
    char [][] tablero = new char [tamanoX] [tamanoY];
    for (int i = 0; i < tamanoX; i++) {
        for (int j=0; j < tamanoY; j++) {
            tablero[i][j] = '-';
        }
    }

    // Esta función ha creado un tablero con todos los valores '-' según las dimensiones que nos marcan de 10x10
   
    return tablero; // Para que se pueda visualizar la situación del tablero.

}

        //** FUNCIÓN MOSTRAR TABLERO <==== 

        // Ahora sigamos con MOSTRAR TABLERO (habrá dos formas de mostrarse), usaremos bucle for e if y el uso de char y booleanos.
        // Mostrará el tablero con opciones true o false, con true muestra el contenido completo del tauler
        // Con false mostrara solo los disparos X y A y ocultará la psociión de los barcos

        

        public static void mostrarTablero(char[][] tablero, boolean visualizacionCompleta) { // Con esto muestra la visualización en forma de tablero con el uso de los |
            // al principip tuve problemas con el encaje de los parámetros puesto que solo coloqué un | y se desplazaba. 
            System.out.print("   ");
            for (int i = 1; i <= tablero.length; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.println("  ---------------------");// no he conseguido encuadrar bien los numeros con las líneas, solamente he podido hacerlo visual
        
            for (int i = 0; i < tablero.length; i++) {
                if (i < 9) {
                    System.out.print((i + 1) + " | ");
                } else {
                    System.out.print((i + 1) + "| ");
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
                    System.out.print(contenido + " ");
                }
                System.out.println();
            }
            System.out.println("  ---------------------");
        }
        

//=======> FUNCIÓN COORDENADA ALEATORIA (insertaré las coordinadas y los barcos) <======= 
        
        //Vamos con la función de COORDENADA ALEATORIA, aquí usaremos el método random

public static int[] coordenadaAleatoria(char[][] tablero, int tamañoBarco) {
    Random random = new Random();
    int tamañoFilas = tablero.length;
    int tamañoColumnas = tablero [0].length;

    int fila = random.nextInt(tamañoFilas); // genera una fila aleatoria 
    int columna = random.nextInt(tamañoColumnas - tamañoBarco + 1);// genera la columna aleatoria pero teiendo en cuenta el tamaño del barco
    // por eso he añadido el tamañoBarco +1 

    return new int[] {fila,columna};
}

        //(Función de insertar el barco mediante el metodo random aleatorio, como vi en la parte 5.5 he usado el metodo random 
        //para que de manera aleatoria se aplque al tamaño de las filas y de las columnas y luego cuando insertemos barcos aleatorios
        // se aplique esta función con el char 
public static void insertarBarcosAleatorio(char[][] tablero, int lanchas, int barcos, int acorazado, int portaaviones) {
    insertarBarco(tablero, 'L',lanchas, 1);
    insertarBarco(tablero, 'B',barcos, 3);
    insertarBarco(tablero, 'Z',acorazado, 4);
    insertarBarco(tablero, 'P',portaaviones,5);
}
// Aquí sigo las instrucciones indicadas del PDF en cuanto a la inserción dde los barcos

private static void insertarBarco(char[][] tablero, char tipoBarco, int cantidad, int tamaño) {
    Random random = new Random();
    int intentosMaximos = 100; // Aquí pongo el numero maximo de intentos tal y como se nos dice en el apartado de 5.5 en cuanto no sea capaz de conseguirse 
    int tamañoFilas = tablero.length; // obtengo tamaño de las filas usando lenght
    int tamañoColumnas = tablero[0].length; // obtengo tamaño de las columnas usnado lenght



    for (int i = 0; i < cantidad; i++) {   // Este bucle lo que hará es intentar insertar la cantidad de barcos especificada en el tablero que aparece
        int intentos = 0; // contador de intentos 
        boolean barcoInsertado = false; // esta variable se encarga de la verificación 

        while (intentos < intentosMaximos && !barcoInsertado) { // Y aquí entra en juego el bucle while que se usa para que
            // mientras no hayamos alcanzado el número máximo de intentos y el barco no esté insertado genero coordenadas aleatorias dentro del tablero para intentar insertar el barco
            int fila = random.nextInt(tamañoFilas);
            int columna = random.nextInt(tamañoColumnas - tamaño + 1);


            // Aquí verifico si es posible insertar el barco en estas coordenadas.
            if (validarInsercion(tablero, fila, columna, tamaño)) {
                insertarBarcoEnTablero(tablero, tipoBarco, fila, columna, tamaño);
                barcoInsertado = true;
            }
            
            intentos++; // Sumamos un intento por cada vez que intentamos insertar un barco
        }

        if (!barcoInsertado) {
            System.out.println("ERROR: No se pudo insertar el barco de tipo " + tipoBarco); // si no se pudo insertar hago que salga
            // un mensaje en pantalla
        }
    }
}

        //Validamos las inserciones metidas por el jugador
private static boolean validarInsercion(char[][] tablero, int fila,int columna, int tamaño) {
    int tamañoColumnas = tablero[0].length; 

    if (columna + tamaño > tamañoColumnas) {
        return false; // Es lógico porque significa que el barco se sale del tablero

    }

    for (int i = columna; i< columna + tamaño; i++) {

        if (tablero[fila][i] != '-') {

            return false; // Aquí pongo false porque ya hay un barco en ese sitio

        }
    }

    return true;
}

    private static void insertarBarcoEnTablero(char[][] tablero, char tipoBarco, int fila, int columna, int tamaño) {
    for (int i = columna; i < columna + tamaño; i++) {
        tablero[fila][i] = tipoBarco;
    }

}

////==> MEOTOD DE SALIDA(SURTIDA/TRET) "PEDIMOS COORDENADAS" (desarrollo las funciones para los tiros) <====  

    public static int[] pedirCoordenadas() { // Creo un objeto Scanner para leer la entrada del usuario me gusta más que el Read console
        Scanner scanner = new Scanner(System.in);
            int[] coordenadas = new int[2]; // Creo un arrayy para almacenar las coordenadas [fila y columna].

        System.out.println("Introduce la fila del tiro (número): ");
            coordenadas[0] = scanner.nextInt();

        System.out.println("Introduce la columna del tiro (número): ");
        coordenadas[1] = scanner.nextInt();
        
   // scanner.close(); // Aqui cerraba el objeto Scanner para liberar recursos pero si lo cerraba salía un error posteriormente y no he sabido resolverlo
        // si me comentas el código por favor, ayudame a entender como puedo cerrarlo 

    return coordenadas;
        
    }
    
    // Aqui se inserta la funcion de Procesar el Tiro , la función de pedirCoordenadas lo que hace es que
    //solicita las coordenadas al jugador y la funcion de procesar tiro verifica si es un aciertom un fallo 
    // o si ya se habia cogido esa posición y entonces asi se queda la tabla actualizada


    public static void procesarTiro(char[][] tableroOrdenador, char[][] tableroJugador, int[] coordenadas) {
        int fila = coordenadas[0]; // Extraigo la fila de las coordenadas proporcionadas
        int columna = coordenadas[1]; // lo mismo con las columnas 
    
        if (fila < 0 || fila >= tableroOrdenador.length || columna < 0 || columna >= tableroOrdenador[0].length) {
            System.out.println("Las coordenadas introducidas no son válidas. Inténtalo de nuevo.");
            return;
        } // Con el if lo que hago es verifico si las coordenadas están dentro de los límites del tablero.
     
        char contenido = tableroOrdenador[fila][columna]; // Aquí obtengo el contenido en la casilla del tablero del enemigo
    
        System.out.println("Contenido en esa casilla: " + contenido); // y con esto se mostrará el contenido de la casilla atacada.
        if (tableroJugador[fila][columna] == 'X' || tableroJugador[fila][columna] == 'A') {
            System.out.println("¡Error! Ya has hecho un tiro en esta casilla."); // Aqui con el if se destaca si ya he tocado la casilla 
        } else if (contenido == '-') { // si no está tocada entonces aparecerá agua y la letra A
            System.out.println("¡Agua!");
            tableroJugador[fila][columna] = 'A';
        } else {
            System.out.println("¡Tocado!"); // si no se dan otras opciones y se ha acertado se abre simbolo X y aparece 
            tableroJugador[fila][columna] = 'X';
        }
    }

public static boolean quedanBarcosSinHundir(char[][] tablero) { // con el for pretendo iterar sobre las filas del tablero viendo si quedan barcos sin hundir
    for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
            if (tablero[i][j] != '-' && tablero[i][j] != 'X' && tablero[i][j] != 'A') {
                return true; // Si hay algún barco que no está hundido ni marcado como que está tocado o fallado
            }
        }
    }
    
    return false; // Si no se encontraron barcos sin que se hayan hundido
}

//Ahora en primer lugar se muestra el tablero completo con la disposición de los barcos y los tiros realizados por el jugador.
public static void finDePartida(char[][] tableroJugador, char[][] tableroOrdenador) {
    System.out.println("Tablero completo con barcos y tiros:");
    mostrarTablero(tableroJugador, true);

    if (quedanBarcosSinHundir(tableroOrdenador)) { // el ifverifica si quedan barcos sin hundir en el tablero del enemigo
        System.out.println("¡Has perdido! Aún quedan barcos enemigos.");
    } else {
         System.out.println("¡Has ganado! Has hundido todos los barcos enemigos.");
    }

    System.out.println("¡Gracias porr jugar!"); // este será el mensaje final que aparecerá al jugador e
    
}

}


