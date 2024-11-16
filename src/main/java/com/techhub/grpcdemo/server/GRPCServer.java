package com.techhub.grpcdemo.server;

import com.techhub.grpcdemo.services.EmployeeService;
import com.techhub.grpcdemo.services.OrderService;
import com.techhub.grpcdemo.services.ReceiptService;
import com.techhub.grpcdemo.util.CMDLArgumentParser;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;

import io.grpc.Server;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.protobuf.services.ProtoReflectionService;
import lombok.extern.slf4j.Slf4j;

/**
 * The GRPC Server class for GRPC services
 *
 * @author Ram Niwash
 */
@Slf4j
public class GRPCServer {

    /* The Default Port number of Server */
    private static final int DEFAULT_PORT_NUMBER = 9080;

    /* PORT constant */
    private static final String PORT = "-port";

    /* PORT_REGEX constant */
    private static final String PORT_REGEX = "[0-9]{4}";

    /* Defines the environment variable name for GRPC Server port number */
    private static final String SYSTEM_ENV_GRPC_PORT = "GRPC_SERVER_PORT";

    /* PORT_NUMBER of Server */
    private final int PORT_NUMBER;

    /**
     * Constructor to initialize the server port.
     */
    public GRPCServer() {
        this.PORT_NUMBER = this.getPortNumber();
    }

    /**
     * Initialize and Starts the GRPC server
     *
     * @throws Exception if something went wrong.
     */
    public void initializeAndStart() throws Exception {
        log.info("Initializing The GRPCServer");
        Server server = Grpc.newServerBuilderForPort(this.PORT_NUMBER, InsecureServerCredentials.create())
                .addService(ProtoReflectionService.newInstance())
                /* Add Services here */
                .addService(new EmployeeService())
                .addService(new OrderService())
                .addService(new ReceiptService())
                .build();

        /* Another Alternative approach to create the server */
        //Server server = ServerBuilder.forPort(this.PORT_NUMBER).addService(new EmployeeService()).build();

        /* Starting Server */
        log.info("Starting the Server");
        server.start();
        ServerReadyEvent.onServerReady(this.PORT_NUMBER);
        server.awaitTermination();
    }

    /**
     * Get the server port number
     *
     * @return integer, the port number
     */
    private int getPortNumber() {
        String port;
        try {
            port = CMDLArgumentParser.getArgumentValue(PORT);
        } catch (RuntimeException exception) {
            port = System.getenv(SYSTEM_ENV_GRPC_PORT);
        }
        return parsePortNumber(port);
    }

    /**
     * Parse the Port number from String to integer.
     *
     * @param port String type argument
     * @return int value as port number
     */
    private int parsePortNumber(String port) {
        if (!StringUtil.isNullOrEmpty(port) && port.matches(PORT_REGEX)) {
            return Integer.parseInt(port);
        } else {
            return DEFAULT_PORT_NUMBER;
        }
    }
}