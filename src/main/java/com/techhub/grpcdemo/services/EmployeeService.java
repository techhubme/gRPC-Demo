package com.techhub.grpcdemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techhub.grpc.services.employee.EmployeeRequestData;
import com.techhub.grpc.services.employee.EmployeeResponseData;
import com.techhub.grpc.services.employee.EmployeeServiceGrpc;

import com.techhub.grpcdemo.config.Constant;
import com.techhub.grpcdemo.dto.EmployeeDTO;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeService GRPC Service
 *
 * @author Ram Niwash
 */
@Slf4j
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    /* The ObjectMapper */
    private final ObjectMapper objectMapper;

    public EmployeeService(){
        this.objectMapper = new ObjectMapper();
        //this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //this.objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        //this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public StreamObserver<EmployeeRequestData> addEmployee(StreamObserver<EmployeeResponseData> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(EmployeeRequestData requestData) {
                EmployeeResponseData.Builder builder = EmployeeResponseData.newBuilder();

                try {
                    log.info(Constant.LINE);
                    EmployeeDTO employeeDTO = objectMapper.convertValue(requestData, EmployeeDTO.class);
                    log.info("EmployeeDTO\n{}", employeeDTO.toString());
                    log.info(Constant.LINE);
                    builder.setMsg(Constant.REQUEST_PROCESSED_SUCCESSFULLY);
                }catch (Exception ex){
                    log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                    builder.setMsg(Constant.EXCEPTION_ERROR);
                }
                responseObserver.onNext(builder.build());
            }

            @Override
            public void onError(Throwable ex) {
                log.error(Constant.EXCEPTION_STACK_TRACE, ex);
                EmployeeResponseData responseData = EmployeeResponseData.newBuilder()
                        .setMsg(Constant.EXCEPTION_ERROR).build();
                responseObserver.onNext(responseData);
            }

            @Override
            public void onCompleted() {
                EmployeeResponseData responseData = EmployeeResponseData.newBuilder()
                        .setMsg(Constant.STREAM_CLOSED).build();
                responseObserver.onNext(responseData);
                responseObserver.onCompleted();
            }
        };
    }
}
