package br.rcmto.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Carro (@Column(unique = true, nullable = false) val placa: String, @Column(length = 60, nullable = false) val modelo: String){
    @Id
    @GeneratedValue
    val id: Long? = null
}