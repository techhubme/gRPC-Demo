package com.techhub.grpcdemo.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * CMDLArgumentParserTest cases
 *
 * @author Ram Niwash
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CMDLArgumentParserTest {

    /* The COMMAND LINE ARGUMENTS */
    private static final String[] CMDL_ARGS = {"-port:4500", "-arg1:Hello", "-arg2:89", "-arg3:u87g",
            "-arg4:jyoti", "-arg5"};

    @Test
    @Order(1)
    void parseTest() {
        CMDLArgumentParser.parse(CMDL_ARGS);
        Assertions.assertTrue(Boolean.TRUE);
    }

    @ParameterizedTest
    @MethodSource("getArgumentTestInputs")
    @Order(2)
    void getArgumentTest(String cmdlKey, String cmdlVal) {
        String value = CMDLArgumentParser.getArgumentValue(cmdlKey);
        Assertions.assertEquals(cmdlVal, value);
    }

    private static Stream<Arguments> getArgumentTestInputs() {
        return Stream.of(Arguments.arguments("-port", "4500"),
                Arguments.arguments("-arg1", "Hello"),
                Arguments.arguments("-arg2", "89"),
                Arguments.arguments("-arg3", "u87g"),
                Arguments.arguments("-arg4", "jyoti"));
    }

    @Test
    @Order(3)
    void getArgumentTestException() throws RuntimeException {
        Executable executable = () -> {
            CMDLArgumentParser.getArgumentValue("arg5");
        };
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, executable);
        Assertions.assertEquals("Command line argument not found", ex.getMessage());
    }
}
