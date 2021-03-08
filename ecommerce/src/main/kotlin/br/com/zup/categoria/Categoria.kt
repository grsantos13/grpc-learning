package br.com.zup.categoria

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Entity
class Categoria(
    @field:NotBlank
    @field:Column(nullable = false)
    val nome: String,
    @field:Valid
    @field:ManyToOne
    val categoriaMae: Categoria? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
