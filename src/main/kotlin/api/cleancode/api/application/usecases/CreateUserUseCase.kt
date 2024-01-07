// Pacote da Camada de Casos de Uso - Interface de Caso de Uso
package lojas.longo.lojaslongoestoque.application.usecases

import lojas.longo.lojaslongoestoque.core.entity.User

interface CreateUserUseCase {
    fun execute(username: String, password: String): User
}
