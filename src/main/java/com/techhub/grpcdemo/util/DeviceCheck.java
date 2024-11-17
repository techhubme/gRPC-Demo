package com.techhub.grpcdemo.util;

import com.techhub.grpcdemo.config.Constant;
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
        try {
            UsbServices services = UsbHostManager.getUsbServices();
            UsbHub usbHub = services.getRootUsbHub();
            listDevices(usbHub);
        } catch (UsbException exception) {
            log.error(Constant.EXCEPTION_STACK_TRACE, exception);
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
