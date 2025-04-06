//package com.techhub.grpcdemo.services;
//
//import com.techhub.grpc.services.device.Device;
//import com.techhub.grpc.services.device.DeviceRequestData;
//import com.techhub.grpc.services.device.DeviceResponseData;
//import com.techhub.grpc.services.device.DeviceServiceGrpc;
//import com.techhub.grpcdemo.config.Constant;
//import com.techhub.grpcdemo.repo.DeviceRepo;
//import io.grpc.stub.StreamObserver;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
///**
// * The DeviceService implementations
// *
// * @author Ram Niwash
// */
//@Slf4j
//@AllArgsConstructor
//public class DeviceService extends DeviceServiceGrpc.DeviceServiceImplBase {
//
//    public DeviceRepo deviceRepo;
//
//    @Override
//    public StreamObserver<DeviceRequestData> getSystemDeviceList(StreamObserver<DeviceResponseData> responseObserver) {
//
//        return new StreamObserver<>() {
//
//            @Override
//            public void onNext(DeviceRequestData value) {
//                List<Device> devices = deviceRepo.getAllDevices();
//                DeviceResponseData deviceResponseData = DeviceResponseData.newBuilder()
//                        .setMsg(String.format("%s%d%s", "FOUND ", devices.size(), " DEVICES"))
//                        .addAllDevices(devices)
//                        .build();
//                responseObserver.onNext(deviceResponseData);
//            }
//
//            @Override
//            public void onError(Throwable ex) {
//                log.error(Constant.EXCEPTION_STACK_TRACE, ex);
//                DeviceResponseData responseData = DeviceResponseData.newBuilder()
//                        .setMsg(Constant.EXCEPTION_ERROR).build();
//                responseObserver.onNext(responseData);
//            }
//
//            @Override
//            public void onCompleted() {
//                DeviceResponseData responseData = DeviceResponseData.newBuilder()
//                        .setMsg(Constant.STREAM_CLOSED).build();
//                responseObserver.onNext(responseData);
//                responseObserver.onCompleted();
//            }
//        };
//    }
//}
