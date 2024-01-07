package lojas.longo.lojaslongoestoque.adapters.impl

import lojas.longo.lojaslongoestoque.application.usecases.CreateProductUseCase
import lojas.longo.lojaslongoestoque.core.ProductGateway
import lojas.longo.lojaslongoestoque.core.entity.Product
import org.springframework.stereotype.Service

@Service
class CreateProductUseCaseImpl(private val productGateway: ProductGateway) : CreateProductUseCase {
    override fun execute(name:String, size: String, brand: String, color:String, gender:String) : Product{
        // Lógica de negócios para criação de produto
        val newProduct = Product(null,name,size,brand,color,gender)
        return productGateway.save(newProduct)
    }
}