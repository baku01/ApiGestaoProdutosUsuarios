// Pacote da Camada de Domínio - Interface de Repositório
package lojas.longo.lojaslongoestoque.core

import lojas.longo.lojaslongoestoque.core.entity.User

interface UserGateway {
    fun save(user: User): User
    fun findById(id: Long): User?
}
