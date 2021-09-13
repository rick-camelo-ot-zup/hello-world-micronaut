package br.rcmto.controller

import br.rcmto.dto.CarroRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.transaction.SynchronousTransactionManager
import io.micronaut.validation.Validated
import java.sql.Connection
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/carros")
class CarroController(private val entityManager: EntityManager,
                      private val transactionManager: SynchronousTransactionManager<Connection>
) {

    @Post
    fun cadastraCarro (@Body @Valid request: CarroRequest): HttpResponse<Any?>{
        val carro = request.toModel()
        transactionManager.executeWrite {
            entityManager.persist(carro)
        }
        val uri = UriBuilder.of("/carros/{id}").expand(mutableMapOf(Pair("id", carro.id)))
        return HttpResponse.created(uri)
    }

    @Get
    fun listaCarros(): HttpResponse<Any?>{
        val resultList = transactionManager.executeRead {
            val query = entityManager.createQuery("SELECT c FROM Carro c")
            query.resultList
        }
        return HttpResponse.ok(resultList)
    }
}