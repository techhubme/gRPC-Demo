package com.techhub.grpcdemo.services;

import com.google.protobuf.util.JsonFormat;
import com.techhub.grpc.services.order.OrderRequestData;
import com.techhub.grpc.services.order.OrderResponseData;
import com.techhub.grpc.services.order.OrderServiceGrpc;
import com.techhub.grpcdemo.config.Constant;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderService GRPC Service
 *
 * @author Ram Niwash
 */
@Slf4j
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase{

    /* JSON Format Printer */
    private final JsonFormat.Printer printer = JsonFormat.printer();

    @Override
    public StreamObserver<OrderRequestData> placeOrder(StreamObserver<OrderResponseData> responseObserver) {

        return new StreamObserver<>() {

            @Override
            public void onNext(OrderRequestData orderRequestData) {
                log.info("OrderRequestData ::::::::::");
                log.info("ORDER ID = {}",orderRequestData.getOrderId());
                log.info("ORDER TYPE = {}",orderRequestData.getOrderType());
                log.info("HAS DISCOUNT = {}",orderRequestData.getHasDiscount());
                log.info("ITEM = {}",orderRequestData.getItems(0));
                log.info("STORE ID DISCOUNT = {}",orderRequestData.getStore().getStoreId());
                log.info("ORDER REQUEST {}",orderRequestData);

                try {
                    log.info(Constant.LINE);
                    String json = printer.print(orderRequestData);
                    log.info("ORDER JSON :{}", json);
                }catch (Exception ex){
                    log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                }

                OrderResponseData responseData = OrderResponseData.newBuilder()
                        .setMsg(Constant.REQUEST_PROCESSED_SUCCESSFULLY)
                        .build();

                responseObserver.onNext(responseData);
            }

            @Override
            public void onError(Throwable ex) {
                log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                OrderResponseData responseData = OrderResponseData.newBuilder()
                        .setMsg(Constant.EXCEPTION_ERROR)
                        .build();
                responseObserver.onNext(responseData);
            }

            @Override
            public void onCompleted() {
                OrderResponseData responseData = OrderResponseData.newBuilder()
                        .setMsg(Constant.STREAM_CLOSED)
                        .build();
                responseObserver.onNext(responseData);
                responseObserver.onCompleted();
            }
        };
    }
}
