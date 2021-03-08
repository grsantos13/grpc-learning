package br.com.zup.categoria

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/categoria")
class NovaCategoriaController(private val manager: EntityManager) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: NovaCategoriaRequest) : HttpResponse<CategoriaResponse> {
        val categoria = request.toModel(manager)
        manager.persist(categoria)
        return HttpResponse.ok(CategoriaResponse(categoria))
    }
}