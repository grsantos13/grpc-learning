package br.com.zup.gateway.service

import br.com.zup.gateway.PaymentRequest
import br.com.zup.gateway.PaymentResponse
import br.com.zup.gateway.PaymentServiceGrpc
import com.google.protobuf.Any
import com.google.rpc.Code
import com.google.rpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.grpc.stub.StreamObserver
import javax.inject.Singleton
import br.com.zup.gateway.Status as StatusEnum

@Singleton
class PaymentService : PaymentServiceGrpc.PaymentServiceImplBase() {

    override fun pay(request: PaymentRequest, responseObserver: StreamObserver<PaymentResponse>) {

        when {
            request.cartao.startsWith("1") -> {
                val e = criarErro(
                    Code.INVALID_ARGUMENT,
                    "Dados incorretos.",
                    StatusEnum.DADOS_INCORRETOS
                )
                responseObserver.onError(e)
            }
            request.cartao.contains("5432") -> {
                val e = criarErro(
                    Code.INVALID_ARGUMENT,
                    "Saldo insuficiente.",
                    StatusEnum.SALDO_INSUFICIENTE
                )
                responseObserver.onError(e)
            }
            request.cartao.contains("1234") -> {
                val e = criarErro(
                    Code.INTERNAL,
                    "Erro interno",
                    StatusEnum.UNKNOWN_ERROR
                )
                responseObserver.onError(e)
            }
            else -> {
                val response = PaymentResponse.newBuilder()
                    .setStatus(StatusEnum.SUCESSO)
                    .build()
                responseObserver.onNext(response)
                responseObserver.onCompleted()
            }
        }

    }

    private fun criarErro(code: Code, message: String, status: StatusEnum): StatusRuntimeException {
        val status = Status.newBuilder()
            .setCode(code.number)
            .setMessage(message)
            .addDetails(
                Any.pack(
                    PaymentResponse.newBuilder()
                        .setStatus(status)
                        .build()
                )
            )
            .build()
        return StatusProto.toStatusRuntimeException(status)
    }
}