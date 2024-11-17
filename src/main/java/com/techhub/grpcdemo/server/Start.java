package com.techhub.grpcdemo.server;

import com.techhub.grpcdemo.config.Constant;
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
            /* Parsing the Command line arguments */
            CMDLArgumentParser.parse(args);

            /* Creating, initializing and starting the GRPC server */
            GRPCServer server = new GRPCServer();
            server.initialize();
            server.start();
        } catch (Exception exception) {
            log.error(Constant.EXCEPTION_STACK_TRACE, exception);
        }
    }
}