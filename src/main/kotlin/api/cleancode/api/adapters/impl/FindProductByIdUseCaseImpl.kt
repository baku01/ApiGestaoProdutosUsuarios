package lojas.longo.lojaslongoestoque.adapters.impl

import lojas.longo.lojaslongoestoque.application.usecases.FindProductByIdUseCase
import lojas.longo.lojaslongoestoque.core.ProductGateway
import lojas.longo.lojaslongoestoque.core.entity.Product
import org.springframework.stereotype.Service


@Service
class FindProductByIdUseCaseImpl(private val productGateway: ProductGateway): FindProductByIdUseCase {

    override fun exeecute(id: Long): Product? {
        return productGateway.findById(id)
    }
}