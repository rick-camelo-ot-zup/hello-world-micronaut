package br.rcmto.repository

import br.rcmto.dto.AutorResponse
import br.rcmto.model.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface AutorRepository : CrudRepository<Autor?, Long?>{

    fun findAllOrderByCriadoEmDesc(pageable: Pageable): List<Autor>
}