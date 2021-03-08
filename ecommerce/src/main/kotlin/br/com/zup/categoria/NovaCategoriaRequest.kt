package br.com.zup.categoria

import br.com.zup.shared.validation.ExistsResource
import br.com.zup.shared.validation.Unique
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus.NOT_FOUND
import io.micronaut.http.exceptions.HttpStatusException
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank

@Introspected
data class NovaCategoriaRequest(
    @field:NotBlank
    @field:Unique(field = "nome", domainClass = Categoria::class)
    val nome: String,
    @field:ExistsResource(field = "id", domainClass = Categoria::class)
    val idCategoriaMae: Long? = null
) {

    fun toModel(manager: EntityManager): Categoria {
        var categoriaMae: Categoria? = null
        if (idCategoriaMae != null)
            categoriaMae = manager.find(Categoria::class.java, idCategoriaMae)
                ?: throw HttpStatusException(NOT_FOUND, "Categoria não encontrada para o id $idCategoriaMae")

        return Categoria(
            nome = nome,
            categoriaMae = categoriaMae
        )
    }

}
