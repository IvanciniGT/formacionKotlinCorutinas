package curso

import dagger.Binds
import dagger.Module

@Module
abstract class ModuloHacedor {
    @Binds
    abstract fun bindHacedor(hacedorDeCosas: HacedorDeCosas): IHacedorDeCosas
}