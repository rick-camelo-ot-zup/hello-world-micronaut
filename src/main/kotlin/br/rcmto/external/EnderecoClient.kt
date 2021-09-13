import br.rcmto.external.dto.ViaCepDTO
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Client("http://viacep.com.br/ws")
interface EnderecoClient{

    @Get("/{cep}/json/")
    fun consulta(@PathVariable cep: String): HttpResponse<ViaCepDTO>
}