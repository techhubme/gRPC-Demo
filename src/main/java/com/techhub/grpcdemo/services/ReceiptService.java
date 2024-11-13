package com.techhub.grpcdemo.services;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.techhub.grpc.services.receipt.ReceiptRequestData;
import com.techhub.grpc.services.receipt.ReceiptResponseData;
import com.techhub.grpc.services.receipt.ReceiptServiceGrpc;
import com.techhub.grpcdemo.config.Constants;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiptService extends ReceiptServiceGrpc.ReceiptServiceImplBase {

    private JsonFormat.Printer printer = JsonFormat.printer();

    @Override
    public StreamObserver<ReceiptRequestData> printReceipt(StreamObserver<ReceiptResponseData> responseObserver) {

        return new StreamObserver<>() {
            @Override
            public void onNext(ReceiptRequestData requestData) {
                try {
                    String requestJson = printer.print(requestData);
                    log.info(Constants.LINE);
                    log.info("REQUEST BODY JSON\n{}", requestJson);
                    log.info(Constants.LINE);
                    log.info("REQUEST BODY\n{}", requestData);
                    log.info(Constants.LINE);
                } catch (InvalidProtocolBufferException ex) {
                    log.info(Constants.EXCEPTION_OCCURRED);
                    log.error(ex.getMessage(), ex);
                    log.info(Constants.STACK_TRACE_ENDS);
                }
            }

            @Override
            public void onError(Throwable ex) {
                log.info(Constants.EXCEPTION_OCCURRED);
                log.error(ex.getMessage(), ex);
                log.info(Constants.STACK_TRACE_ENDS);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
