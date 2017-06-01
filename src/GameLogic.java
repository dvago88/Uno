
import java.util.Iterator;
import java.util.TreeSet;

public class GameLogic {

    public String whatToPlay(String card, TreeSet<String> cards, int acumulado) {
        String posible1;//Para recorrer el arbol y guardar una posible2 coincidencia por color
        int cantidadColores[] = {0, 0, 0, 0};//A M V R
        Iterator<String> iterator;
        if ((card.charAt(0) == 'D' || card.charAt(0) == 'B') && acumulado != 0) {// si no hay un +2 o +4 en mesa con acumulado
            iterator = cards.iterator();
            String posible2 = "sin carta";
            while (iterator.hasNext()) {// Buscamos otro +2 o +4 sea el caso
                posible1 = iterator.next();
                if (card.charAt(0) == posible1.charAt(0)) {
                    posible2 = posible1;
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
                if (cards.remove(posible2)) {
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
                        return posible2;
                    }else{
                        return (posible2);
                    }       
                }
            } 
            return ("sin carta");
        } else {//Si no hay +2 o +4 en mesa
            iterator = cards.iterator();
            String posible2 = "sin carta"; // para guardar una posible2 coincidencia de numero o carta si no hay color
            String posible3 = "sin carta";// Guardar un posible2 cambio de color o un +4 como última opción
            while (iterator.hasNext()) {//Buscamos color primeramente en to-do el maso o sino coincidencia de carta
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

                if (card.charAt(1) == posible1.charAt(1)) {// si coincide el color de carta en mesa y carta de la maquina de inmediato se juega
                    iterator.remove();
                    return (posible1);
                } else if (card.charAt(0) == posible1.charAt(0) && posible1.charAt(0)!='C'&& posible1.charAt(0)!='B') {// si coincide numero o tipo de carta
                    posible2 = posible1;
                } else if (posible1.charAt(0) == 'C' || posible1.charAt(0) == 'B') {// si tenemos un cambio de color o un +4
                    posible3 = posible1;
                }
            }
            if (!posible2.equals("sin carta")) {// Si la carta no coincide por color pero si por numero o tipo de carta
                if (cards.remove(posible2)) {
                    return posible2;
                }
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
                if (cards.remove(aux)) {
                    return posible3;
                }
                return "sin carta";
            } else {
                return posible3;
            }
        }
    }
}
