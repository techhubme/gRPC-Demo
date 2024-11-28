package com.techhub.grpcdemo.repo;

import com.techhub.grpc.services.device.Device;
import org.usb4java.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation for DeviceRepo
 *
 * @author Ram Niwash
 */
public class LocalDeviceRepo implements DeviceRepo {

    @Override
    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();

        Context context = new Context();
        int result = LibUsb.init(context);

        DeviceList devicesList = new DeviceList();
        LibUsb.getDeviceList(context, devicesList);

        for (org.usb4java.Device device : devicesList) {
            /* Create Device Builder*/
            Device.Builder builder = Device.newBuilder();

            DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
            LibUsb.getDeviceDescriptor(device, deviceDescriptor);

            DeviceHandle deviceHandle = new DeviceHandle();
            result = LibUsb.open(device, deviceHandle);
            if (result == 0) {
                StringBuffer buffer = new StringBuffer(1024);
                int byteRead = LibUsb.getStringDescriptorAscii(deviceHandle, deviceDescriptor.iProduct(), buffer);
                String vendorId = Integer.toHexString((int) deviceDescriptor.idVendor());
                String productId = Integer.toHexString((int) deviceDescriptor.idProduct());
                vendorId = String.format("%4s", vendorId).replace(' ', '0');
                productId = String.format("%4s", productId).replace(' ', '0');
                builder.setDeviceDescription(buffer.toString())
                        .setVendorId(vendorId)
                        .setProductId(productId);
                /* Add Device to list */
                devices.add(builder.build());
            }
        }

        return devices;
    }
}
