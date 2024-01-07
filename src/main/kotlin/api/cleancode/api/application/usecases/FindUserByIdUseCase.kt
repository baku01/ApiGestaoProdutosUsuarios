package lojas.longo.lojaslongoestoque.application.usecases

import lojas.longo.lojaslongoestoque.core.entity.User

interface FindUserByIdUseCase {
    fun execute(id:Long): User?
}