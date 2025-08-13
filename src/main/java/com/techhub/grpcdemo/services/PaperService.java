package com.techhub.grpcdemo.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.techhub.grpc.services.paper.PaperRequestData;
import com.techhub.grpc.services.paper.PaperResponseData;
import com.techhub.grpc.services.paper.PaperServiceGrpc;
import com.techhub.grpc.services.receipt.ReceiptRequestData;
import com.techhub.grpc.services.receipt.ReceiptResponseData;
import com.techhub.grpcdemo.config.Constant;
import com.techhub.grpcdemo.dto.PaperData;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * Paper Service
 *
 * @author Ram Niwash
 */
@Slf4j
public class PaperService extends PaperServiceGrpc.PaperServiceImplBase {

    private final ObjectMapper objectMapper;

    public PaperService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public StreamObserver<PaperRequestData> printPaper(StreamObserver<PaperResponseData> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(PaperRequestData requestData) {
                try {
                    String requestJson = requestData.getRequestData();
                    log.info("REQUEST PAYLOAD = {}", requestJson);
                    PaperData paperData = objectMapper.readValue(requestJson, PaperData.class);
                    log.info("PaperData \n - {}\n", paperData);
                    responseObserver.onNext(PaperResponseData.newBuilder().setMessage("SUCCESS").build());
                } catch (Exception ex) {
                    log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                    responseObserver.onNext(PaperResponseData.newBuilder().setMessage("FAILED").build());
                }
            }

            @Override
            public void onError(Throwable ex) {
                log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                responseObserver.onNext(PaperResponseData.newBuilder().setMessage("FAILED").build());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
