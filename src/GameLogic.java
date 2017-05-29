
import java.util.Iterator;
import java.util.TreeSet;

public class GameLogic {

    private String mPlayedCard;
    private TreeSet<String> mCards;

    public String whatToPlay(String card, TreeSet<String> cards, int acumulado) {
        System.out.print("La maquina escoge carta -> ");//Borrar esto
        mPlayedCard = card;
        mCards = cards;
        String posible1;//Para recorrer el arbol y guardar una posible2 coincidencia por color
        int cantidadColores[] = {0, 0, 0, 0};//A M V R
        Iterator<String> iterator;
        if ((mPlayedCard.charAt(0) == 'D' || mPlayedCard.charAt(0) == 'B') && acumulado != 0) {// si no hay un +2 o +4 en mesa con acumulado
            System.out.print("la maquina debe arrastrar cartas -> ");//Borrar esto
            iterator = mCards.iterator();
            String posible2 = "sin carta";
            while (iterator.hasNext()) {// Buscamos otro +2 o +4 sea el caso
                posible1 = iterator.next();
                if (mPlayedCard.charAt(0) == posible1.charAt(0)) {
                    posible2 = posible1;
                    System.out.print("la maquina ha encontrado una solucion -> ");//Borrar esto
                }
                switch (posible1.charAt(1)) {// Contamos los colores en total
                    case 'A': {
                        cantidadColores[0]++;
                        break;
                    }
                    case 'M': {
                        cantidadColores[1]++;
                        break;
                    }
                    case 'V': {
                        cantidadColores[2]++;
                        break;
                    }
                    case 'R': {
                        cantidadColores[3]++;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            if (!posible2.equals("sin carta")) {//si hallo alguna carta
                if (mCards.remove(posible2)) {
                    if (posible2.charAt(0) == 'B') {
                        int mayor = cantidadColores[0];
                        int pos = 0;
                        for (int i = 1; i < 4; i++) {// Escogemos el color que tiene mayor presencia en el maso
                            if (cantidadColores[i] > mayor) {
                                mayor = cantidadColores[i];
                                pos = i;
                            }
                        }
                        switch (pos) {
                            case 0:
                                posible2 = posible2.substring(0, 1) + "A" + posible2.substring(2, 3);
                                break;
                            case 1:
                                posible2 = posible2.substring(0, 1) + "M" + posible2.substring(2, 3);
                                break;
                            case 2:
                                posible2 = posible2.substring(0, 1) + "V" + posible2.substring(2, 3);
                                break;
                            case 3:
                                posible2 = posible2.substring(0, 1) + "R" + posible2.substring(2, 3);
                                break;
                        }
                        System.out.print("Maquina ha jugado +4 y escoge color " + posible2 + " -> ");// Borrar esto
                        return posible2;
                    }else{
                        System.out.print("Maquina ha jugado +2 -> ");//Borrar esto
                        return (posible2);
                    }       
                }
            } 
            System.out.print("esta vez la maquina no se ha salvado! -> ");//Borrar esto
            return ("sin carta");
        } else {//Si no hay +2 o +4 en mesa
            iterator = mCards.iterator();
            String posible2 = "sin carta"; // para guardar una posible2 coincidencia de numero o carta si no hay color
            String posible3 = "sin carta";// Guardar un posible2 cambio de color o un +4 como última opción
            System.out.print("Buscando posible carta... -> ");//Borrar esto
            while (iterator.hasNext()) {//Buscamos color primeramente en todo el maso o sino coincidencia de carta
                posible1 = iterator.next();

                switch (posible1.charAt(1)) {// Contamos los colores en total
                    case 'A': {
                        cantidadColores[0]++;
                        break;
                    }
                    case 'M': {
                        cantidadColores[1]++;
                        break;
                    }
                    case 'V': {
                        cantidadColores[2]++;
                        break;
                    }
                    case 'R': {
                        cantidadColores[3]++;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                if (mPlayedCard.charAt(1) == posible1.charAt(1)) {// si coincide el color de carta en mesa y carta de la maquina de inmediato se juega
                    System.out.print("Maquina ha jugado color "+ posible1 + " -> ");//Borrar esto
                    iterator.remove();
                    return (posible1);
                } else if (mPlayedCard.charAt(0) == posible1.charAt(0)) {// si coincide numero o tipo de carta
                    System.out.print("Maquina ha encontrado numero o de tipo de carta -> ");//Borrar esto
                    System.out.print(posible1 + " -> ");
                    posible2 = posible1;
                } else if (posible1.charAt(0) == 'C' || posible1.charAt(0) == 'B') {// si tenemos un cambio de color o un +4
                    System.out.print("Maquina ha encontrado carta especial -> ");//Borrar esto
                    posible3 = posible1;
                }
            }
            if (!posible2.equals("sin carta")) {// Si la carta no coincide por color pero si por numero o tipo de carta
                if (mCards.remove(posible2)) {
                    System.out.print("Maquina ha jugado carta por numero o tipo de carta  " + posible2 +" -> ");//Borrar esto
                    return posible2;
                }
                System.out.println("Mierda, algo ha pasado 1");//Borrar esto
                return "sin carta";
            } else if (!posible3.equals("sin carta")) {
                String aux = posible3;
                int mayor = cantidadColores[0];
                int pos = 0;
                for (int i = 1; i < 4; i++) {// Escogemos el color de mayor presencia
                    if (cantidadColores[i] > mayor) {
                        mayor = cantidadColores[i];
                        pos = i;
                    }
                }
                switch (pos) {
                    case 0:
                        posible3 = posible3.substring(0, 1) + "A" + posible3.substring(2, 3);
                        break;
                    case 1:
                        posible3 = posible3.substring(0, 1) + "M" + posible3.substring(2, 3);
                        break;
                    case 2:
                        posible3 = posible3.substring(0, 1) + "V" + posible3.substring(2, 3);
                        break;
                    case 3:
                        posible3 = posible3.substring(0, 1) + "R" + posible3.substring(2, 3);
                        break;
                }
                if (mCards.remove(aux)) {
                    System.out.print("Maquina ha jugado carta especial "+posible3+" -> ");//Borrar esto
                    return posible3;
                }
                System.out.println("Mierda algo ha pasado 2");//Borrar esto
                return "sin carta";
            } else {
                return posible3;
            }
        }
    }
}
