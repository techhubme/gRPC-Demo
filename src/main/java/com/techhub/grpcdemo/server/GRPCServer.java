package com.techhub.grpcdemo.server;

import com.techhub.grpcdemo.services.EmployeeService;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * The GRPC Server class for GRPC services
 *
 * @author Ram Niwash
 */
@Slf4j
public class GRPCServer {

    private final int PORT_NUMBER;

    public GRPCServer(int portNumber){
        this.PORT_NUMBER = portNumber;
    }

    /**
     * Initialize and Starts the GRPC server
     *
     * @throws Exception if something went wrong.
     */
    public void initializeAndStart() throws Exception {
        log.info("Initializing The GRPCServer");
        Server server = Grpc.newServerBuilderForPort(PORT_NUMBER, InsecureServerCredentials.create())
                .addService(new EmployeeService())
                .build();
//        Server server = ServerBuilder.forPort(this.PORT_NUMBER).addService(new EmployeeService()).build();
        log.info("Server Starting");
        server.start();
        log.info("Server Started and listening on port {}",this.PORT_NUMBER);
    }
}
