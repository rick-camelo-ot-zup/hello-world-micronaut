package br.rcmto.dto

import br.rcmto.model.Carro
import io.micronaut.core.annotation.Introspected
import validator.UniqueValue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import kotlin.math.max

@Introspected
data class CarroRequest (
    @field:NotBlank
    @field:Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    @field:UniqueValue(
        message = "Esta placa já está cadastrada.",
        domainClassName = "Carro",
        field = "placa"
    )
    val placa: String,
    @field:Size(max=60)
    val modelo: String
) {
    fun toModel(): Carro {
        return Carro(placa,modelo)
    }
}