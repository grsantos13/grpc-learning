package br.com.zup.usuario

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/usuarios")
class NovoUsuarioController(private val manager: EntityManager) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: NovoUsuarioRequest): HttpResponse<Any> {
        val usuario: Usuario = request.toModel()
        manager.persist(usuario)
        return HttpResponse.ok()
    }
}