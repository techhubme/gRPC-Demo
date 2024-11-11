package com.techhub.grpcdemo.services;

import com.techhub.grpc.services.order.OrderRequestData;
import com.techhub.grpc.services.order.OrderResponseData;
import com.techhub.grpc.services.order.OrderServiceGrpc;
import com.techhub.grpcdemo.config.Constants;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * The Order service
 *
 * @author Ram Niwash
 */
@Slf4j
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase{

    @Override
    public StreamObserver<OrderRequestData> placeOrder(StreamObserver<OrderResponseData> responseObserver) {
        return new StreamObserver<OrderRequestData>() {

            @Override
            public void onNext(OrderRequestData orderRequestData) {
                log.info("OrderRequestData ::::::::::");
                log.info("ORDER ID = {}",orderRequestData.getOrderId());
                log.info("ORDER TYPE = {}",orderRequestData.getOrderType());
                log.info("HAS DISCOUNT = {}",orderRequestData.getHasDiscount());
                log.info("ITEM = {}",orderRequestData.getItems(0));
                log.info("STORE ID DISCOUNT = {}",orderRequestData.getStore().getStoreId());
                log.info("REQUEST {}",orderRequestData.toString());
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
