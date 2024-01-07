package lojas.longo.lojaslongoestoque.adapters.gateway

import jakarta.persistence.*
import org.springframework.data.relational.core.mapping.Column

@Entity
@Table(name = "Products")
data class ProductEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val name:String,

    @Column
    val size: String,

    @Column
    val brand: String,

    @Column
    val color:String,

    @Column
    val gender:String)

