package com.techhub.grpcdemo.server;

import com.techhub.grpcdemo.util.CMDLArgumentParser;
import lombok.extern.slf4j.Slf4j;

/**
 * The Main class and starting point of the application.
 *
 * @author Ram Niwash
 */
@Slf4j
public class Start {

    /**
     * The Starting point of application.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        try {
            /* Running some Pre-process jobs before running the server */
            CMDLArgumentParser.parse(args);

            /* Creating, initializing and starting the GRPC server */
            GRPCServer server = new GRPCServer();
            server.initializeAndStart();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}