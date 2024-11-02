package com.techhub.grpcdemo.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * The startup code that run after application server startup
 * 
 * @author Ram Niwash
 * @since 1.0.0
 */
@Slf4j
public class ApplicationReadyEvent {

	/** The WELCOME_MSG Constant */
	public static final String WELCOME_LINE = "WELCOME TO GRPC-Demo Application";

	/** The LINE Constant */
	public static final String LINE = "---------------------------------------------------------------------";

	/** The NEW_LINE Constant */
	private static final String NEW_LINE = "\n";

	/** The HOST Constant */
	private static final String HOST = "HOST";

	/** The ONE_SPACE Constant */
	private static final String ONE_SPACE = " ";

	/** The COLON Constant */
	private static final String COLON = ":";

	public static void onServerReady(int portNumber) {
		StringBuilder hosts = new StringBuilder(NEW_LINE);
		hosts.append(LINE);
		hosts.append(NEW_LINE);
		hosts.append(WELCOME_LINE);
		hosts.append(NEW_LINE);
		hosts.append(LINE);
		try {
			Enumeration<NetworkInterface> enumerationNW = NetworkInterface.getNetworkInterfaces();
			while (enumerationNW.hasMoreElements()) {
				NetworkInterface networkInterface = enumerationNW.nextElement();
				Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses();
				while (enumeration.hasMoreElements()) {
					InetAddress inetAddress = enumeration.nextElement();
					hosts.append(NEW_LINE).append(HOST)
							.append(ONE_SPACE).append(COLON)
							.append(ONE_SPACE).append(inetAddress.getHostAddress())
							.append(COLON).append(portNumber);
				}
				hosts.append(NEW_LINE);
				hosts.append(LINE);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.info(hosts.toString());
	}
}