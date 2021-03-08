package br.com.zup.pagamento

import br.com.zup.gateway.PaymentResponse
import br.com.zup.gateway.PaymentServiceGrpc
import br.com.zup.gateway.Status
import br.com.zup.shared.StatusConversion
import com.google.protobuf.Any
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Inject
import br.com.zup.pagamento.NovoPagamentoRequest as PR

@Controller("/pagamentos")
class NovoPagamentoController(@Inject val grpcClient: PaymentServiceGrpc.PaymentServiceBlockingStub) {

    @Post
    fun pagar(@Body request: PR): NovoPagamentoResponse {
        val paymentRequest = request.toGrpcPaymentRequest()
        try {
            val paymentResponse = grpcClient.pay(paymentRequest)
            return NovoPagamentoResponse(status = paymentResponse.status)
        } catch (e: StatusRuntimeException) {
            val statusCode = e.status.code
            val statusDescription = e.status.description
            val status = StatusConversion.valueOf(statusCode.name)

            val statusProto = StatusProto.fromThrowable(e)
                ?: throw HttpStatusException(status.httpStatus, statusDescription)

            val details: Any = statusProto.detailsList[0]
            val statusResponse = details.unpack(PaymentResponse::class.java)

            throw HttpStatusException(
                status.httpStatus,
                "Descrição erro: $statusDescription \n Status: ${statusResponse.status}"
            )
        }
    }
}

data class NovoPagamentoResponse(val status: Status)