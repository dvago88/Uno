# Uno

Projecto para la universidad, recrear el juego de mesa UNO.

Se utilizo un LinkedList para meter las "cartas" iniciales y se utiliza Collection.shuffle para barajarlas. 

A todos los jugadores se les almacenan las cartas en un TreeSet.

Todavía no se tiene un manejo apropiado de programación orientada a objetos lo cual genera que haya cosas que probablemente hubieran podido ser mejor hechas, sin embargo se hace de esta forma con el objetivo de practicar y mejorar, además de tratar de darle un orden al codigo asignandole tareas especificas a cada clase.

La táctica del juego es básica, siempre el computador revisará si tiene color si no lo tiene busca si tiene número, y si no tiene ninguna de las anteriores busca si tiene cartas especiales. Nunca arrastrá sin necesidad y núnca se le olvidará decir "UNO". Esto garantiza una probabilidad de ganar de 1/4 si se juego contra 3 rivales.

El juego tiene una introducción inicial para ayudar a entender la mecanica del mismo, sin embargo se apela al sentido común el saber utilizarlo.



