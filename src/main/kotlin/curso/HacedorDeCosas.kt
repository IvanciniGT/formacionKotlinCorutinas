package curso

import corutinas.contar
import kotlinx.coroutines.*
import javax.inject.Inject



class HacedorDeCosas @Inject constructor(private val clienteDelServicio: ClienteServicio ) : IHacedorDeCosas {

    // OPCION 1... que pudo haber sido... pero que NO        var userId = 0;

    override fun hacerCosas() {

        // Crear los datos de una nueva persona

        runBlocking{
            val creador: Deferred<Int> = async {// Está pensado para tareas que devuelven algo
                crearPersona()
            }
            val contador:Job = launch {// Launch está más pensado para tareas que no devuelven nada
                                                    // El launch devuelve un JOB
                contar("ContadorQuEsperaAlAlta", 200, 200)
            }
            // Que cuando acabe el primero, se cancele el segundo

            // A veces me puede interesar esto... otras... es algo que quiero dejar configurado...
            // pero ir haciendo mas cosas mientras
            //creador.join() // Espero a que acabe el primero
            //contador.cancel()

            // Si no me interesa hacer pausa... y quiero poder seguir haciendo más cosas: CALLBACK
            creador.invokeOnCompletion { // Cuando acabe el primero... quiero hacer algo
                contador.cancel()
            }
            println("Sigo haciendo cosas")
            creador.join(); // No veuelve nada... mientras que el await, devuelve lo que devolviese la función que lancé con el async
            println("Se acabó")

            // Quiero llamar a otro trabajo con un dato que el primero devuelve
            recuperarPersona(creador.await()); // El await es bloqueante... igual que el join.
        }
    }

    suspend fun crearPersona(): Int{
        delay(1000)
        val datosNuevaPersona = DatosNuevaPersona("Juan", 30,"juanito@juanito.com","12345678A")
        val respuesta = clienteDelServicio.nuevaPersona(datosNuevaPersona)
        println("Ya tengo la respuesta del post")
        println(respuesta.isSuccessful)
        println(respuesta.code())
        println(respuesta.body())
        println(respuesta.body()?.id)
        // OPCION 1 que pudo haber sido           userId = respuesta.body()?.id ?: 0
        return respuesta.body()?.id ?: 0
    }

    suspend fun recuperarPersona(id: Int){
        val respuesta = clienteDelServicio.recuperarPersona(id)
        println("Ya tengo la respuesta del get")
        println(respuesta.isSuccessful)
        println(respuesta.code())
        println(respuesta.body())
        println(respuesta.body()?.id)
    }

}