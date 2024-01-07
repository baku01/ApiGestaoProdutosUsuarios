// Pacote da Camada de Adaptadores - Implementação do Gateway
package lojas.longo.lojaslongoestoque.adapters.gateway

import lojas.longo.lojaslongoestoque.core.UserGateway
import lojas.longo.lojaslongoestoque.core.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

interface UserRepository : CrudRepository<UserEntity, Long>

@Service
class UserGatewayImpl(private val userRepository: UserRepository) : UserGateway {
    override fun save(user: User): User {
        // Lógica de persistência aqui
        val entity = UserEntity(null,user.username,user.password)
        val savedEntity = userRepository.save(entity)
        return User(savedEntity.id, savedEntity.username, savedEntity.password)
    }

    override fun findById(id: Long): User {
        // Lógica de busca por ID
        val optionalEntity = userRepository.findById(id)
        return if (optionalEntity.isPresent) {
            val entity = optionalEntity.get()
            User(entity.id, entity.username, entity.password)
        } else {
            throw error("Error")
        }
    }
    // Implemente outros métodos conforme necessário
}
