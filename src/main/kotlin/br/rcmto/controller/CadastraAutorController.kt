package br.rcmto.controller
import EnderecoClient
import br.rcmto.dto.NovoAutorRequest
import br.rcmto.repository.AutorRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.validator.Validator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.validation.ConstraintViolation


@Controller("/autores")
class CadastraAutorController(
    val repository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    var log: Logger = LoggerFactory.getLogger(javaClass)

    @Inject
    var validator: Validator? = null

    @Post
    fun cadastraAutor(@Body request: NovoAutorRequest): HttpResponse<Any> {
        val constraintViolations: Set<ConstraintViolation<NovoAutorRequest>> =
            validator?.let { it.validate(request).toSet() } as Set<ConstraintViolation<NovoAutorRequest>>
        if (constraintViolations.size > 0) {
            var errors: Map<String, String> =
                constraintViolations.map { it.propertyPath.toString() to it.message }.toMap()
            return HttpResponse.badRequest(errors)
        }

        //FAZER REQUISICAO PARA SERVIÇO EXTERNO
        val endereco = enderecoClient.consulta(request.cep)
        println(endereco)


        log.info("Requisicao de cadastro de autor recebida: $request com endereco: $endereco")
        val autor = request.paraAutor()
        log.info("Objeto do tipo Autor: $autor")
        repository.save(autor)
        return HttpResponse.created(request);
    }

    @Get
    fun buscaPorId(
        @QueryValue(defaultValue = "0") pagina: Int,
        @QueryValue(defaultValue = "10") tamanho: Int
    ): HttpResponse<Any> {
        val pageable = Pageable.from(pagina, tamanho)
        val result = repository.findAllOrderByCriadoEmDesc(pageable)
        val page = Page.of(result, pageable, result.size.toLong())
        return HttpResponse.ok(page)
    }
}