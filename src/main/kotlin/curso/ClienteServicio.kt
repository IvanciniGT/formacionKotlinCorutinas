package curso

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ClienteServicio {

    @POST("/api/v1/user")
    suspend fun nuevaPersona(@Body datosDeLaNuevaPersona: DatosNuevaPersona): Response<DatosPersona>

    @GET("api/v1/user/{id}")
    suspend fun recuperarPersona(@Path("id") id: Int): Response<DatosPersona>

    @GET("api/v1/user") //?sort=nombre
    suspend fun recuperarPersonas(@Query("sort") campo:String, @Header("Authorization") token:String): Response<List<DatosPersona>>

    @FormUrlEncoded
    @POST("/api/v1/user")
    suspend fun actualizarPersona(@Field("edad") edad:Int):Response<DatosPersona>

    @Multipart
    @POST("/api/v1/user")
    suspend fun actualizarPersona(@Part("foto") foto: RequestBody):Response<DatosPersona>

}