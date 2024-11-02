package com.techhub.grpcdemo.services;

import com.techhub.grpc.services.employee.EmployeeRequest;
import com.techhub.grpc.services.employee.EmployeeResponse;
import com.techhub.grpc.services.employee.EmployeeServiceGrpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * Implements the EmployeeService
 *
 * @author Ram Niwash
 */
@Slf4j
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase{


}
