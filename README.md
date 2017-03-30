# Uno

Projecto para la universidad, recrear el juego de mesa UNO. Inicialmente solo con las cartas numéricas exceptuando el cero.

Se utilizo un arreglo para meter las "cartas" iniciales y barajarlas con un metodo propio, luego estas se pasa a un queue de strings que que como implementacion tiene Arraydeque, más adelante se utiliza una lista enlazada y se utiliza Collection.shuffle para barajarlas. Esta diferencia en la forma de hacerlo solo se debe a querer practicar diferentes formas de lograr barajar las cartas.

A todos los jugadores se les almacenan las cartas en una lista enlazada.

Todavía no se tiene un manejo apropiado de programación orientada a objetos lo cual genera que haya cosas que probablemente hubieran podido ser mejor hechas, sin embargo se hace de esta forma con el objetivo de practicar y mejorar, además de tratar de darle un orden al codigo asignandole tareas especificas a cada clase.

La táctica del juego es básica, siempre el computador revisará si tiene color y de lo contrario buscara si tiene número. Nunca arrastrá sin necesidad y núnca se le olvidará decir "UNO". Esto garantiza una probabilidad de ganar de 1/4 si se juego contra 3 rivales.

El juego tiene una introducción inicial para ayudar a entender la mecanica del mismo, sin embargo se apela al sentido común el saber utilizarlo.



