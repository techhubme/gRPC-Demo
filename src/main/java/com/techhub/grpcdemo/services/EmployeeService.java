package com.techhub.grpcdemo.services;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase{

    @Override
    public StreamObserver<EmployeeRequest> addNewEmployee(StreamObserver<EmployeeResponse> responseObserver) {

        return new StreamObserver<EmployeeRequest>() {
            @Override
            public void onNext(EmployeeRequest value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }
}
