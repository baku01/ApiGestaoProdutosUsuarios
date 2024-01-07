// Pacote da Camada de Adaptadores - Implementação do Caso de Uso
package lojas.longo.lojaslongoestoque.adapters.impl

import lojas.longo.lojaslongoestoque.application.usecases.CreateUserUseCase
import lojas.longo.lojaslongoestoque.core.UserGateway
import lojas.longo.lojaslongoestoque.core.entity.User
import org.springframework.stereotype.Service

@Service
class CreateUserUseCaseImpl(private val userGateway: UserGateway) : CreateUserUseCase {
    override fun execute(username: String, password: String): User {
        // Lógica de negócios para criação de usuário
        val newUser = User(null,username, password)
        return userGateway.save(newUser)
    }
}
