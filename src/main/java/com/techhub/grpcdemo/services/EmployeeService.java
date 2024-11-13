package com.techhub.grpcdemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techhub.grpc.services.employee.EmployeeRequestData;
import com.techhub.grpc.services.employee.EmployeeResponseData;
import com.techhub.grpc.services.employee.EmployeeServiceGrpc;

import com.techhub.grpcdemo.config.Constants;
import com.techhub.grpcdemo.dto.EmployeeDTO;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * Implements the EmployeeService
 *
 * @author Ram Niwash
 */
@Slf4j
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private final ObjectMapper objectMapper;

    public EmployeeService(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public StreamObserver<EmployeeRequestData> addEmployee(StreamObserver<EmployeeResponseData> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(EmployeeRequestData requestData) {
                try {
                    log.info(Constants.LINE);
                    EmployeeDTO employeeDTO = objectMapper.convertValue(requestData, EmployeeDTO.class);
                    log.info("EmployeeDTO\n{}", employeeDTO.toString());
                    log.info(Constants.LINE);
                }catch (Exception ex){
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
