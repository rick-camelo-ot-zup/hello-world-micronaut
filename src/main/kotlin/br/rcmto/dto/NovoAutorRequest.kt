package br.rcmto.dto

import br.rcmto.model.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest (
    @field:NotBlank
    @field:Size(max = 60)
    private val nome: String?,
    @field:NotBlank
    @field:Email
    @field:Size(max = 42)
    private val email: String?,
    private val livros: Set<LivroDto>?,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
    ){
    fun paraAutor(): Autor {
        return Autor(nome.orEmpty(), email.orEmpty())
    }
}
