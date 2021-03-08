package br.com.zup.payment

import br.com.zup.gateway.PaymentServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClientFactory {

    @Singleton
    fun paymentClientStub(@GrpcChannel("gateway") channel: ManagedChannel): PaymentServiceGrpc.PaymentServiceBlockingStub? {
        return PaymentServiceGrpc
            .newBlockingStub(channel)
    }
}