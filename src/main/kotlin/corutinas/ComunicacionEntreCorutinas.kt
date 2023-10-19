package corutinas

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main () {

    runBlocking {
        println("Iniciando procesos")
        var canalDeComunicacion: Channel<String> = Channel()

        val productorDeDatos = launch {
            for (i in 1..10) {
                canalDeComunicacion.send("Produciendo dato $i")
                delay(1000)
            }
            canalDeComunicacion.close()
        }
        val consumidorDeDatos = launch {
            for(mensaje in canalDeComunicacion) {
                println("Consumiendo dato: $mensaje")
            }
        }
        println("Procesos iniciados")

        productorDeDatos.join()
        consumidorDeDatos.join()
        println("Todo listo")
    }

}