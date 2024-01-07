package lojas.longo.lojaslongoestoque.core

import lojas.longo.lojaslongoestoque.core.entity.Product

interface ProductGateway {
    fun save(product: Product): Product
    fun findById(id: Long): Product?
}