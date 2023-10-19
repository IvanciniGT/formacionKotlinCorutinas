package curso

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitUtils {

    fun configurarRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun obtenerClienteDelServicio(retrofit:Retrofit): ClienteServicio {
        return retrofit.create(ClienteServicio::class.java)
    }
}