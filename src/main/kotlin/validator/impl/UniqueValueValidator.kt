package validator.impl

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.transaction.SynchronousTransactionManager
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import validator.UniqueValue
import java.sql.Connection
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
class UniqueValueValidator(private val entityManager: EntityManager,
                           private val transactionManager: SynchronousTransactionManager<Connection>
) : ConstraintValidator<UniqueValue, String> {

    private lateinit var domainAttribute: String
    private lateinit var kClassName: String

    override fun initialize(params: UniqueValue) {
        domainAttribute = params.field
        kClassName = params.domainClassName
    }
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueValue>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value.equals("")) return false

        return transactionManager.executeRead {
            val query = entityManager.createQuery("SELECT c FROM $kClassName c WHERE c.$domainAttribute = '$value'")
            query.resultList.isEmpty()
        }
    }

}