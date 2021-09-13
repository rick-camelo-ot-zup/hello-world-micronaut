package br.rcmto.dto

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class AutorResponse(
    var nome: String,
    var criadoEm: LocalDateTime
) {
}