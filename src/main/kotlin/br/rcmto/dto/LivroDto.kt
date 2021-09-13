package br.rcmto.dto

import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

@Introspected
data class LivroDto (
    @field:NotBlank
    private val titulo: String,
    @field:NotBlank
    private val isbn: String,
    @field:PastOrPresent
    private val publicadoEm: LocalDate){
}