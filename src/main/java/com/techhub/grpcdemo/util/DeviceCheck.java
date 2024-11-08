package com.techhub.grpcdemo.util;

import com.techhub.grpcdemo.config.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.usb.*;
import java.util.Collections;

/**
 * Print the details of connected devices with System.
 *
 * @author Ram Niwash
 */
@Slf4j
public final class DeviceCheck {

    public static void showAllDevices() {
        log.info(Constants.LINE);
        log.info("LIST OF CONNECTED DEVICES");
        try {
            UsbServices services = UsbHostManager.getUsbServices();
            UsbHub usbHub = services.getRootUsbHub();
            listDevices(usbHub);
        } catch (UsbException exception) {
            log.info(Constants.EXCEPTION_OCCURRED);
            log.error(exception.getMessage(), exception);
            log.info(Constants.STACK_TRACE_ENDS);
        }
    }

    public static void listDevices(UsbHub hub) {
        var devices = hub.getAttachedUsbDevices();
        for (Object o : Collections.unmodifiableList(devices)) {
            UsbDevice device = (UsbDevice) o;
            log.info("DEVICE {}", device.toString());
            if (device.isUsbHub()) {
                listDevices((UsbHub) device);
            }
        }
    }
}
