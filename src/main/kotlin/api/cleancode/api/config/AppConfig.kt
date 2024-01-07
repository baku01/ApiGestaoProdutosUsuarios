// Pacote da Camada de Configuração
package lojas.longo.lojaslongoestoque.config

import lojas.longo.lojaslongoestoque.adapters.gateway.UserGatewayImpl
import lojas.longo.lojaslongoestoque.adapters.gateway.UserRepository
import lojas.longo.lojaslongoestoque.adapters.impl.CreateUserUseCaseImpl
import lojas.longo.lojaslongoestoque.application.usecases.CreateUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig(private val userRepository: UserRepository) {

    @Bean
    fun createUserUseCase(): CreateUserUseCase {
        return CreateUserUseCaseImpl(UserGatewayImpl(userRepository))
    }


}
