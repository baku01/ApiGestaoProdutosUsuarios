package lojas.longo.lojaslongoestoque.adapters.gateway

import lojas.longo.lojaslongoestoque.core.ProductGateway
import lojas.longo.lojaslongoestoque.core.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service


interface ProductRepository : CrudRepository <ProductEntity, Long>

@Service
class ProductGatewayImpl (private val productRepository: ProductRepository) : ProductGateway{
    override fun save(product: Product): Product {
        // Lógica de persistência aqui
        val entity = ProductEntity(null, product.name, product.size, product.brand, product.color, product.gender)
        val savedEntity = productRepository.save(entity)
        return Product(savedEntity.id, savedEntity.name, savedEntity.size, savedEntity.brand, savedEntity.color, savedEntity.gender)
    }

    override fun findById(id: Long): Product {
        // Lógica de busca por ID
        val optionalEntity = productRepository.findById(id)
        return if (optionalEntity.isPresent) {
            val entity = optionalEntity.get()
            Product(entity.id, entity.name, entity.size, entity.brand, entity.color, entity.color)
        }else{
            throw error("Error")
        }
    }

}