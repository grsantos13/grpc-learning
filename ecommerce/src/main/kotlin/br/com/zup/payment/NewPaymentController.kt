package br.com.zup.payment

import br.com.zup.shared.StatusConversion
import br.com.zup.gateway.PaymentServiceGrpc
import br.com.zup.gateway.Status
import com.google.protobuf.Any
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Inject
import br.com.zup.payment.PaymentRequest as PR

@Controller("/payment")
class NewPaymentController(@Inject val grpcClient: PaymentServiceGrpc.PaymentServiceBlockingStub) {

    @Post
    fun pay(@Body request: PR): PaymentResponse {
        val paymentRequest = request.toGrpcPaymentRequest()
        try {
            val paymentResponse = grpcClient.pay(paymentRequest)
            return PaymentResponse(status = paymentResponse.status)
        } catch (e: StatusRuntimeException) {
            val statusCode = e.status.code
            val statusDescription = e.status.description
            val status = StatusConversion.valueOf(statusCode.name)

            val statusProto = StatusProto.fromThrowable(e)
                ?: throw HttpStatusException(status.httpStatus, statusDescription)

            val details: Any = statusProto.detailsList[0]
            val statusResponse = details.unpack(br.com.zup.gateway.PaymentResponse::class.java)

            throw HttpStatusException(status.httpStatus, "Descrição erro: $statusDescription \n Status: ${statusResponse.status}")
        }
    }
}

data class PaymentResponse(val status: Status)