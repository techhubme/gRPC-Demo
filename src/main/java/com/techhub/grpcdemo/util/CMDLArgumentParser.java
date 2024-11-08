package com.techhub.grpcdemo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * The Command line argument parser to parse and extract the arguments.
 *
 * @author Ram Niwash
 */
public class CMDLArgumentParser {

    /* COMMAND_ARG_PREFIX constant */
    private static final String COMMAND_ARG_PREFIX = "-";

    /* COMMAND_LINE_ARGS constant, Map for Command line arguments */
    private static final Map<String, String> COMMAND_LINE_ARGS = new HashMap<>();

    /**
     * Parse the command line argument and stores into map
     *
     * @param args the command line arguments.
     */
    public static void parse(String[] args) {
        if (args == null || args.length == 0) {
            return;
        }
        int i = 0;
        while (i < args.length) {
            String argumentKey = args[i];
            if (argumentKey.startsWith(COMMAND_ARG_PREFIX)) {
                if (i + 1 >= args.length) {
                    return;
                }
                String argumentValue = args[i + 1];
                COMMAND_LINE_ARGS.put(argumentKey, argumentValue);
            }
            i += 2;
        }
    }

    /**
     * Return the command line argument by argument key.
     *
     * @param argument the key of command line argument.
     * @return String the value of command line argument.
     */
    public static String getArgumentValue(String argument) {
        argument = COMMAND_ARG_PREFIX + argument;
        if (COMMAND_LINE_ARGS.containsKey(argument)) {
            return COMMAND_LINE_ARGS.get(argument);
        }
        throw new RuntimeException("Command line argument not found");
    }
}