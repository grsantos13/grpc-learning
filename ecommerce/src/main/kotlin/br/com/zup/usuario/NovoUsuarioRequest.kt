package br.com.zup.usuario

import br.com.zup.shared.validation.Unique
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoUsuarioRequest(
    @field:Unique(field = "login", domainClass = Usuario::class)
    @field:NotBlank @field:Email val login: String,
    @field:NotBlank @field:Size(min = 6) val senha: String
) {
    fun toModel(): Usuario {
        return Usuario(
            login = login,
            senha = senha
        )
    }

}
