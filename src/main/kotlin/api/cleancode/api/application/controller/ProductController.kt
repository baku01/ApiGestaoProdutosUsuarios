package lojas.longo.lojaslongoestoque.application.controller

import lojas.longo.lojaslongoestoque.application.requests.ProductRequest
import lojas.longo.lojaslongoestoque.application.usecases.CreateProductUseCase
import lojas.longo.lojaslongoestoque.application.usecases.FindProductByIdUseCase
import lojas.longo.lojaslongoestoque.core.entity.Product
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/product")
class ProductController(private val createProductUseCase: CreateProductUseCase, private val findProductByIdUseCase: FindProductByIdUseCase) {
    @PostMapping("/create")
    fun createUser(@RequestBody productRequest: ProductRequest): Product {
        return createProductUseCase.execute(productRequest.name,productRequest.size,productRequest.brand,productRequest.color,productRequest.gender)
    }

    @GetMapping("{id}")
    fun getProduct(@PathVariable id:Long): Product?{
        return findProductByIdUseCase.exeecute(id)
    }

}