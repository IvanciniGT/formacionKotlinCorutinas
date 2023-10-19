package curso

import corutinas.contar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject



class HacedorDeCosas @Inject constructor(private val clienteDelServicio: ClienteServicio ) : IHacedorDeCosas {

    override fun hacerCosas() {

        // Crear los datos de una nueva persona

        runBlocking{
            launch {
                crearPersona()
            }
            launch {
                contar("ContadorQuEsperaAlAlta", 200, 200)
            }
            // Que cuando acabe el primero, se cancele el segundo
            // Quiero llamar a otro trabajo con un dato que el primero devuelve
        }
    }

    suspend fun crearPersona(){
        delay(1000)
        val datosNuevaPersona = DatosNuevaPersona("Juan", 30,"juanito@juanito.com","12345678A")
        val respuesta = clienteDelServicio.nuevaPersona(datosNuevaPersona)
        print("Ya tengo la respuesta del post")
        println(respuesta.isSuccessful)
        println(respuesta.code())
        println(respuesta.body())
        println(respuesta.body()?.id)
    }

}