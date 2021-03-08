package br.com.zup.pagamento

import br.com.zup.gateway.PaymentRequest
import io.micronaut.core.annotation.Introspected

@Introspected
data class NovoPagamentoRequest(
    val numero: String,
    val titular: String,
    val cvv: String
) {

    fun toGrpcPaymentRequest(): PaymentRequest {
        return PaymentRequest.newBuilder()
            .setCartao(numero)
            .setDv(cvv)
            .setTitular(titular)
            .build()
    }
}