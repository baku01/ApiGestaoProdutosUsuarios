package lojas.longo.lojaslongoestoque.application.usecases

import lojas.longo.lojaslongoestoque.core.entity.Product

interface CreateProductUseCase {
    fun execute(name:String,size: String,brand: String,color:String,gender:String): Product
}