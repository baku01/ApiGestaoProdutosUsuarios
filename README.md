# Sistema de Gestão de Usuários e Produtos API 🚀

Bem-vindo à API de Gestão de Usuários e Produtos, um projeto desenvolvido utilizando Clean Architecture. Esta API permite o gerenciamento de usuários e produtos, proporcionando uma separação clara de responsabilidades em diferentes camadas.

## Entidades

### Usuário (User)

```kotlin
// Pacote da Camada de Domínio - Entidade
package api.cleancode.api.core.entity.core.entity

data class User(val id: Long?, val username: String, val password: String)
```

### Produto (Product)

```kotlin
// Pacote da Camada de Domínio - Entidade
package api.cleancode.api.core.entity.core.entity

data class Product(val id: Long?, val name: String, val size: String, val brand: String, val color: String, val gender: String)
```

Na camada de domínio, as entidades `User` e `Product` representam usuários e produtos, respectivamente.

## Interfaces de Repositório

### Usuário (UserGateway)

```kotlin
// Pacote da Camada de Domínio - Interface de Repositório
package api.cleancode.api.core.entity.core

import api.cleancode.api.core.entity.core.entity.User

interface UserGateway {
    fun save(user: User): User
    fun findById(id: Long): User?
}
```

### Produto (ProductGateway)

```kotlin
// Pacote da Camada de Domínio - Interface de Repositório
package api.cleancode.api.core.entity.core

import api.cleancode.api.core.entity.core.entity.Product

interface ProductGateway {
    fun save(product: Product): Product
    fun findById(id: Long): Product?
}
```

As interfaces `UserGateway` e `ProductGateway` definem contratos para operações de persistência e recuperação de usuários e produtos, respectivamente.

## Casos de Uso

### Criar Usuário (CreateUserUseCase)

```kotlin
// Pacote da Camada de Casos de Uso - Interface de Caso de Uso
package api.cleancode.api.core.entity.application.usecases

import api.cleancode.api.core.entity.core.entity.User

interface CreateUserUseCase {
    fun execute(username: String, password: String): User
}
```

### Criar Produto (CreateProductUseCase)

```kotlin
// Pacote da Camada de Casos de Uso - Interface de Caso de Uso
package api.cleancode.api.core.entity.application.usecases

import api.cleancode.api.core.entity.core.entity.Product

interface CreateProductUseCase {
    fun execute(name: String, size: String, brand: String, color: String, gender: String): Product
}
```

As interfaces `CreateUserUseCase` e `CreateProductUseCase` definem contratos para os casos de uso de criação de usuário e produto, respectivamente.

## Controladores

### Usuário (UserController)

```kotlin
// Pacote da Camada de Aplicação - Controlador
package api.cleancode.api.core.entity.application.controllers

import api.cleancode.api.core.entity.application.requests.UserRequest
import api.cleancode.api.core.entity.application.usecases.CreateUserUseCase
import api.cleancode.api.core.entity.core.entity.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val createUserUseCase: CreateUserUseCase) {

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): User {
        return createUserUseCase.execute(userRequest.username, userRequest.password)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): User? {
        // Lógica para buscar e retornar um usuário por ID
        return null
    }

    data class UserRequest(val username: String, val password: String)
}
```

### Produto (ProductController)

```kotlin
// Pacote da Camada de Aplicação - Controlador
package api.cleancode.api.core.entity.application.controller

import api.cleancode.api.core.entity.application.requests.ProductRequest
import api.cleancode.api.core.entity.application.usecases.CreateProductUseCase
import api.cleancode.api.core.entity.application.usecases.FindProductByIdUseCase
import api.cleancode.api.core.entity.core.entity.Product
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/product")
class ProductController(private val createProductUseCase: CreateProductUseCase, private val findProductByIdUseCase: FindProductByIdUseCase) {
    @PostMapping("/create")
    fun createProduct(@RequestBody productRequest: ProductRequest): Product {
        return createProductUseCase.execute(productRequest.name, productRequest.size, productRequest.brand, productRequest.color, productRequest.gender)
    }

    @GetMapping("{id}")
    fun getProduct(@PathVariable id: Long): Product? {
        return findProductByIdUseCase.execute(id)
    }

    data class ProductRequest(val name: String, val size: String, val brand: String, val color: String, val gender: String)
}
```

Nesta camada, os controladores `UserController` e `ProductController` expõem endpoints para criar e recuperar usuários e produtos via HTTP.

## Gateways de Implementação

### Usuário (UserGatewayImpl)

```kotlin
// Pacote da Camada de Adaptadores - Implementação do Gateway
package api.cleancode.api.core.entity.adapters.gateway

import api.cleancode.api.core.entity.core.UserGateway
import api.cleancode.api.core.entity.core.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

interface UserRepository : CrudRepository<UserEntity, Long>

@Service
class UserGatewayImpl(private val userRepository: UserRepository) : UserGateway {
    override fun save(user: User): User {
        // Lógica de persistência aqui
        val entity = UserEntity(null, user.username, user.password)
        val savedEntity = userRepository.save(entity)
        return User(savedEntity.id, savedEntity.username, savedEntity.password)
    }

    override fun findById(id: Long): User? {
        // Lógica de busca por ID
        val optionalEntity = userRepository.findById(id)
        return if (optionalEntity.isPresent) {
            val entity = optionalEntity.get()
            User(entity.id, entity.username, entity.password)
        } else {
            null
        }
    }
}
```

### Produto (ProductGatewayImpl)

```kotlin
// Pacote da Camada de Adaptadores - Implementação do Gateway
package api.cleancode.api.core.entity.adapters.gateway

import api.cleancode.api.core.entity.core.ProductGateway
import api.cleancode.api.core.entity.core.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

interface ProductRepository : CrudRepository<ProductEntity, Long>

@Service
class ProductGatewayImpl(private val productRepository: ProductRepository) :

 ProductGateway {
    override fun save(product: Product): Product {
        // Lógica de persistência aqui
        val entity = ProductEntity(null, product.name, product.size, product.brand, product.color, product.gender)
        val savedEntity = productRepository.save(entity)
        return Product(savedEntity.id, savedEntity.name, savedEntity.size, savedEntity.brand, savedEntity.color, savedEntity.gender)
    }

    override fun findById(id: Long): Product? {
        // Lógica de busca por ID
        val optionalEntity = productRepository.findById(id)
        return if (optionalEntity.isPresent) {
            val entity = optionalEntity.get()
            Product(entity.id, entity.name, entity.size, entity.brand, entity.color, entity.color)
        } else {
            null
        }
    }
}
```

Nas camadas de adaptadores, as implementações dos gateways `UserGatewayImpl` e `ProductGatewayImpl` realizam operações de persistência e recuperação de usuários e produtos utilizando JPA.

### Lista de `TODOs` para Implementação de Exceções

1. **Classe CreateUserUseCaseImpl:**
   - [ ] Implementar tratamento de exceção para casos especiais.

2. **Classe UserGatewayImpl:**
   - [ ] Adicionar lógica de exceção para situações específicas.
   - [ ] Tratar casos relacionados a consultas por ID.

3. **Classe ProductGatewayImpl:**
   - [ ] Implementar lógica de exceção para persistência de produtos.
   - [ ] Tratar casos de busca por ID.

# Como Executar o Projeto ▶️

1. Clone o repositório:

    ```bash
    git clone https://github.com/baku01/ApiGestaoProdutosUsuarios.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd ApiGestaoProdutosUsuarios
    ```

3. Execute o projeto:

    ```bash
    ./gradlew bootRun
    ```

# Utilizando a API

A API oferece endpoints para criar e recuperar usuários e produtos.

## Criar Usuário

```http
POST /api/users
```

Corpo da requisição:

```json
{
    "username": "novoUsuario",
    "password": "senha123"
}
```

## Recuperar Usuário

```http
GET /api/users/{userId}
```

Substitua `{userId}` pelo ID do usuário desejado.

## Criar Produto

```http
POST /api/product/create
```

Corpo da requisição:

```json
{
    "name": "Novo Produto",
    "size": "M",
    "brand": "Marca",
    "color": "Azul",
    "gender": "Feminino"
}
```

## Recuperar Produto

```http
GET /api/product/{id}
```

Substitua `{id}` pelo ID do produto desejado.

# Contribuição 🤝

Sinta-se à vontade para contribuir, abrindo problemas (issues) ou enviando pull requests. Todas as contribuições são bem-vindas!

