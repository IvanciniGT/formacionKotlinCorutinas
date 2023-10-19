package curso

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ModuloRetrofit {

    @Provides
    fun configurarRetrofit(): Retrofit {
        return RetrofitUtils.configurarRetrofit()
    }
    @Provides
    fun obtenerClienteDelServicio(retrofit: Retrofit): ClienteServicio{
        return RetrofitUtils.obtenerClienteDelServicio(retrofit)
    }
}