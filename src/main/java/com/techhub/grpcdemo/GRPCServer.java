package com.techhub.grpcdemo;

import com.techhub.grpcdemo.services.EmployeeService;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;

public class GRPCServer {

    private final int PORT_NUMBER;
    private Server server;

    public GRPCServer(int portNumber){
        this.PORT_NUMBER = portNumber;
    }

    public void initialize(){
        this.server = Grpc.newServerBuilderForPort(PORT_NUMBER, InsecureServerCredentials.create())
                .addService(new EmployeeService())
                .build();

        Runnable shutDownHook = ()->{
            server.shutdown();
        };
        Thread thread = new Thread(shutDownHook);
        Runtime.getRuntime().addShutdownHook(thread);
    }

    public void start() throws IOException {
        this.server.start();
    }

    public void stop(){
        this.server.shutdown();
    }
}
