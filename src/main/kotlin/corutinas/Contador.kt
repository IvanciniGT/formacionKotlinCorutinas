package corutinas

import kotlinx.coroutines.*


suspend fun contar(nombreContador: String , cantidadAContar:Int, velocidad: Long){
    for (i in 1..cantidadAContar) {
        delay(velocidad) // Mientras ésta está durmiendo... quiero poder hacer más cosas
        println("Soy el contador: $nombreContador, y voy por el: $i")
    }
}

fun main(){
    // AQUI CREAREMOS UNA CORRUTINA
    runBlocking {
        withContext(Dispatchers.Default) { // En el trozo que tengo dentro de las llaves, quiero poder hacer uso de suspend Functions...
            println("Vamos a contar")
            // Pero quien ejecuta esos suspend functions es realmente el hilo que venía por aquí
            val tarea1= launch{
                contar("Contador1", 5, 1000)
            }
            val tarea2=launch {
                contar("Contador2", 5, 500)
            }

            // HAcer algo en paralelo con lo anterior
            println("Parece que estamos contando")
            // Voy a esperar a que la tarea2 acabe
            tarea2.join()
            println("La tarea2 ha acabado")
            delay(2000)
            tarea1.cancel()
        }
    }
}

// Tradicionalmente hemos creados hilos de ejecución para diferentes tareas... de forma que se ejecuten esas tareas en paralelo
// El mecanismo de hilos es algo que nos entrega el Sistema Operativo
// También permiten trabajar con procesos
// En un SO, un proceso es capaz de abrir muchos hilos... Siempre tiene al menos 1 hilo
// Cuando creo un proceso:
// - reserva memoria:
//   - Para colocar el propio código del programa en RAM
//   - Para colocar lo datos de trabajo
//   - Para colocar la pila de ejecución (ThreadStack) -> StackOverflow

// El problema de los hilos, es que son muy pesados(Recursos en el SO).. y además el número de hilos que puedo crear es limitado

// Hay lenguajes que tienen una forma alternativa a los hilos: Corutinas
// En JS Sólo hay un hilo de ejecución... pero se pueden crear corutinas.. que en JS se llaman Promesas

// Una función suspend es una porción de código que tiene oportunidad de ser interrumpida (... quedando a la espera de algo) async
// La cuestión es que dentro de una corrutina (algo así como un tipo especial de hilo - Thread), que me permite ejecutar
// muchas funciones suspendidas en paralelo... y que se ejecutan en el mismo hilo de ejecución (corrotina)

// La gracia de las corrutinas es que solo usan 1 hilo de ejecución... y que son muy ligeras de crear
// Tienen poco o nulo impacto a nivel de recursos en el SO... Y puedo crear tantisísisismas funciones suspendidas como quiera

// En Kotlin lo que vamos a tener es un conjunto de corrutinas Predefinidas... podríamos crear las nuestras propias... raro
//