package validator
import validator.impl.UniqueValueValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueValueValidator::class])
@MustBeDocumented
annotation class UniqueValue(
    val message: String = "O valor informado é inválido.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = [],
    val field: String,
    val domainClassName: String
)
