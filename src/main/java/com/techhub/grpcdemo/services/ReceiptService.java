package com.techhub.grpcdemo.services;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.techhub.grpc.services.receipt.ReceiptRequestData;
import com.techhub.grpc.services.receipt.ReceiptResponseData;
import com.techhub.grpc.services.receipt.ReceiptServiceGrpc;
import com.techhub.grpcdemo.config.Constant;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * ReceiptService GRPC Service
 *
 * @author Ram Niwash
 */
@Slf4j
public class ReceiptService extends ReceiptServiceGrpc.ReceiptServiceImplBase {

    /* JSON Format Printer */
    private final JsonFormat.Printer printer = JsonFormat.printer();

    @Override
    public StreamObserver<ReceiptRequestData> printReceipt(StreamObserver<ReceiptResponseData> responseObserver) {

        return new StreamObserver<>() {
            @Override
            public void onNext(ReceiptRequestData requestData) {
                ReceiptResponseData.Builder builder = ReceiptResponseData.newBuilder();
                try {
                    String requestJson = printer.print(requestData);
                    log.info(Constant.LINE);
                    log.info("REQUEST BODY JSON\n{}", requestJson);
                    log.info(Constant.LINE);
                    log.info("REQUEST BODY\n{}", requestData);
                    log.info(Constant.LINE);
                    builder.setMessage(Constant.REQUEST_PROCESSED_SUCCESSFULLY);
                } catch (InvalidProtocolBufferException ex) {
                    log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                    builder.setMessage(Constant.EXCEPTION_ERROR);
                }
                responseObserver.onNext(builder.build());
            }

            @Override
            public void onError(Throwable ex) {
                log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                ReceiptResponseData responseData = ReceiptResponseData.newBuilder()
                        .setMessage(Constant.EXCEPTION_ERROR)
                        .build();
                responseObserver.onNext(responseData);
            }

            @Override
            public void onCompleted() {
                ReceiptResponseData responseData = ReceiptResponseData.newBuilder()
                        .setMessage(Constant.STREAM_CLOSED)
                        .build();
                responseObserver.onNext(responseData);
                responseObserver.onCompleted();
            }
        };
    }
}
