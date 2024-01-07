// Pacote da Camada de Aplicação - Controlador
package lojas.longo.lojaslongoestoque.application.controller

import lojas.longo.lojaslongoestoque.application.requests.UserRequest
import lojas.longo.lojaslongoestoque.application.usecases.CreateUserUseCase
import lojas.longo.lojaslongoestoque.application.usecases.FindUserByIdUseCase
import lojas.longo.lojaslongoestoque.core.entity.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val createUserUseCase: CreateUserUseCase,private val findUserByIdUseCase: FindUserByIdUseCase) {

    @PostMapping("/create")
    fun createUser(@RequestBody userRequest: UserRequest): User {
        return createUserUseCase.execute(userRequest.username, userRequest.password)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User? {
        return findUserByIdUseCase.execute(id)
    }
}
