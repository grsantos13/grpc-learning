package br.com.zup

import io.grpc.Status.Code
import io.micronaut.http.HttpStatus

enum class StatusConversion(
    val grpcStatus: Code,
    val httpStatus: HttpStatus
) {
    OK(Code.OK, HttpStatus.OK),
    CANCELLED(Code.CANCELLED, HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN(Code.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ARGUMENT(Code.INVALID_ARGUMENT, HttpStatus.BAD_REQUEST),
    DEADLINE_EXCEEDED(Code.DEADLINE_EXCEEDED, HttpStatus.REQUEST_TIMEOUT),
    NOT_FOUND(Code.NOT_FOUND, HttpStatus.NOT_FOUND),
    ALREADY_EXISTS(Code.ALREADY_EXISTS, HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED(Code.PERMISSION_DENIED, HttpStatus.FORBIDDEN),
    RESOURCE_EXHAUSTED(Code.RESOURCE_EXHAUSTED, HttpStatus.BAD_GATEWAY),
    FAILED_PRECONDITION(Code.FAILED_PRECONDITION, HttpStatus.PRECONDITION_FAILED),
    ABORTED(Code.ABORTED, HttpStatus.INTERNAL_SERVER_ERROR),
    OUT_OF_RANGE(Code.OUT_OF_RANGE, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE),
    UNIMPLEMENTED(Code.UNIMPLEMENTED, HttpStatus.NOT_IMPLEMENTED),
    INTERNAL(Code.INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR),
    UNAVAILABLE(Code.UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE),
    DATA_LOSS(Code.DATA_LOSS, HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(Code.UNAUTHENTICATED, HttpStatus.UNAUTHORIZED);

}