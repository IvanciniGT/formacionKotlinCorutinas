package curso

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ModuloRetrofit::class, ModuloHacedor::class])
interface ComponenteHacedor {
    fun getHacedor(): IHacedorDeCosas
}