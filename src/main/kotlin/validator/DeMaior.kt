package validator
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.time.LocalDate
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DeMaiorValidator::class])
@MustBeDocumented
annotation class DeMaior(
    val message: String = "O valor informado é inválido.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)

@Singleton
class DeMaiorValidator : ConstraintValidator<DeMaior, LocalDate> {
    override fun isValid(
        value: LocalDate?,
        annotationMetadata: AnnotationValue<DeMaior>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value == null) return true

        val hoje = LocalDate.now();

        var idade = hoje.year - value.year

        if (hoje.month < value.month) {
            idade--
        } else {
            if (hoje.month == value.month && hoje.dayOfMonth < value.dayOfMonth) {
                idade--
            }
        }
        return idade > 18
    }
}