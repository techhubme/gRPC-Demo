package com.techhub.grpcdemo.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.techhub.grpcdemo.config.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * The startup code that run after application server startup
 *
 * @author Ram Niwash
 */
@Slf4j
public class ServerReadyEvent {

    /**
     * Logs the Server URL (IP and Port number) for reference.
     *
     * @param portNumber the port number of server
     */
    public static void onServerReady(int portNumber) {
        StringBuilder hosts = new StringBuilder(Constant.NEW_LINE);
        hosts.append(Constant.LINE);
        hosts.append(Constant.NEW_LINE);
        hosts.append(Constant.WELCOME_MESSAGE);
        hosts.append(Constant.NEW_LINE);
        hosts.append(Constant.LINE);
        try {
            Enumeration<NetworkInterface> enumerationNW = NetworkInterface.getNetworkInterfaces();
            while (enumerationNW.hasMoreElements()) {
                NetworkInterface networkInterface = enumerationNW.nextElement();
                Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses();
                while (enumeration.hasMoreElements()) {
                    InetAddress inetAddress = enumeration.nextElement();
                    hosts.append(Constant.NEW_LINE).append(Constant.HOST).append(Constant.ONE_SPACE)
                            .append(Constant.COLON).append(Constant.ONE_SPACE).append(inetAddress.getHostAddress())
                            .append(Constant.COLON).append(portNumber);
                }
                hosts.append(Constant.NEW_LINE);
                hosts.append(Constant.LINE);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info(hosts.toString());
    }
}