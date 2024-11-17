package com.techhub.grpcdemo.config;

/**
 * Constants defined for reusability.
 *
 * @author Ram Niwash
 */
public final class Constant {

    /**
     * String constants declaration
     */
    /* LINE Constant */
    public static final String LINE = "---------------------------------------------------------------------";

    /* The WELCOME_MSG Constant */
    public static final String WELCOME_MESSAGE = "WELCOME TO GRPC-Demo Application";

    /* The NEW_LINE Constant */
    public static final String NEW_LINE = "\n";

    /* The HOST Constant */
    public static final String HOST = "HOST";

    /* The ONE_SPACE Constant */
    public static final String ONE_SPACE = " ";

    /* The COLON Constant */
    public static final String COLON = ":";

    /* STREAM_CLOSED constant */
    public static final String STREAM_CLOSED = "STREAM CLOSED BY CLIENT";

    /* EXCEPTION_ERROR constant */
    public static final String EXCEPTION_ERROR = "CAUGHT EXCEPTION WHILE PROCESSING REQUEST.";

    /* EXCEPTION_STACK_TRACE_BEGINS constant */
    public static final String EXCEPTION_STACK_TRACE = "[CAUGHT EXCEPTION] EXCEPTION STACK TRACE BEGINS";

    /* REQUEST_PROCESSED_SUCCESSFULLY constant */
    public static final String REQUEST_PROCESSED_SUCCESSFULLY = "REQUEST PROCESSED SUCCESSFULLY.";

    /* COMMAND_ARG_PREFIX constant */
    public static final String COMMAND_ARG_PREFIX = "-";

    /* COMMAND_ARG_SPLITER constant */
    public static final String COMMAND_ARG_SPLITER = ":";

    /* CMDL_ARG_NOT_FOUND constant */
    public static final String CMDL_ARG_NOT_FOUND = "Command line argument not found";

    /* PORT constant */
    public static final String PORT_ARG = "-port";

    /* PORT_REGEX constant */
    public static final String PORT_ARG_REGEX = "[0-9]{4}";

    /* Defines the environment variable name for GRPC Server port number */
    public static final String SYSTEM_ENV_GRPC_PORT = "GRPC_SERVER_PORT";

    /**
     * Number constants declaration
     */
    /* NUMBER_0 constant */
    public static byte NUMBER_0 = 0;

    /* NUMBER_1 constant */
    public static byte NUMBER_1 = 1;

    /* NUMBER_2 constant */
    public static byte NUMBER_2 = 2;

    /* The Default Port number of Server */
    public static final int DEFAULT_GRPC_SERVER_PORT = 9080;
}
