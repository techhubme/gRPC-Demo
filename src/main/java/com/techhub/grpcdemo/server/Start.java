package com.techhub.grpcdemo.server;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * The Main class and starting point of the application.
 *
 * @author Ram Niwash
 */
@Slf4j
public class Start {

    /** The Default Port number of Server */
    private static final int DEFAULT_PORT_NUMBER = 9080;

    /** Defines the environment variable name for GRPC Server port number */
    private static final String SYSTEM_ENV_PORT_NUMBER = "GRPC_SERVER_PORT";

    /**
     * The Starting point of application.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        log.info("::::::: WELCOME TO GRPC SERVER :::::::");
        int portNumber;
        String port;
        if (args == null || args.length == 0) {
            port = System.getenv(SYSTEM_ENV_PORT_NUMBER);
        } else {
            port = args[0];
        }
        portNumber = parsePortNumber(port);
        try{
            GRPCServer server = new GRPCServer(portNumber);
            server.initializeAndStart();
        }catch (Exception exception){
           log.error(exception.getMessage(), exception);
        }
    }

    /**
     * Parse the Port number from String to integer.
     *
     * @param port String type argument
     * @return int value as port number
     */
    private static int parsePortNumber(String port){
        if(!StringUtil.isNullOrEmpty(port) && port.matches("[0-9]{4}")){
            return Integer.parseInt(port);
        }else{
            return DEFAULT_PORT_NUMBER;
        }
    }
}