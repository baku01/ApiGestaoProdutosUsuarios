package lojas.longo.lojaslongoestoque.adapters.impl

import lojas.longo.lojaslongoestoque.application.usecases.FindUserByIdUseCase
import lojas.longo.lojaslongoestoque.core.UserGateway
import lojas.longo.lojaslongoestoque.core.entity.User
import org.springframework.stereotype.Service

@Service
class FindUserByIdUseCaseImpl(private val userGateway: UserGateway) : FindUserByIdUseCase {
    override fun execute(id: Long): User? {
        return userGateway.findById(id)
    }
}
