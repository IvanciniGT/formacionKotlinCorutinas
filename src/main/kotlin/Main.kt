
import curso.DaggerComponenteHacedor
import curso.IHacedorDeCosas

fun main(args: Array<String>) {
    var hacedor: IHacedorDeCosas = DaggerComponenteHacedor.create().getHacedor()
    hacedor.hacerCosas();
}