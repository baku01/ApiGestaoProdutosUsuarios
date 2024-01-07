package lojas.longo.lojaslongoestoque.application.usecases

import lojas.longo.lojaslongoestoque.core.entity.Product

interface FindProductByIdUseCase {

    fun exeecute(id:Long): Product?

}