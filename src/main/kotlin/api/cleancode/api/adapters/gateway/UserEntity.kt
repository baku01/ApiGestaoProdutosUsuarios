// Pacote da Camada de Adaptadores - Entidade JPA
package lojas.longo.lojaslongoestoque.adapters.gateway

import jakarta.persistence.*

@Entity
@Table(name = "Users")
data class UserEntity(
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val username: String,

    @Column
    val password: String
)
