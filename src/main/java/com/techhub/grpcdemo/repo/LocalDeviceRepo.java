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
        LibUsb.init(context);

        DeviceList devicesList = new DeviceList();
        LibUsb.getDeviceList(context, devicesList);

        for (org.usb4java.Device device : devicesList) {

            DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
            LibUsb.getDeviceDescriptor(device, deviceDescriptor);

            DeviceHandle deviceHandle = new DeviceHandle();
            int result = LibUsb.open(device, deviceHandle);
            StringBuffer buffer = null;
            if (result == 0) {
                buffer = new StringBuffer(1024);
                LibUsb.getStringDescriptorAscii(deviceHandle, deviceDescriptor.iProduct(), buffer);
            }
            String vendorId = Integer.toHexString(deviceDescriptor.idVendor());
            String productId = Integer.toHexString(deviceDescriptor.idProduct());
            vendorId = String.format("%4s", vendorId).replace(' ', '0');
            productId = String.format("%4s", productId).replace(' ', '0');

            System.out.println("|******* DEVICE DETAILS *******|");
            System.out.println("Name = " + buffer);
            System.out.println("Vendor ID = " + vendorId);
            System.out.println("Product ID = " + productId);
            System.out.println("SerialNumber = " + deviceDescriptor.iSerialNumber());
            System.out.println("Manufacturer = " + deviceDescriptor.iManufacturer());

            /* Setting the device detail */
            Device.Builder builder = Device.newBuilder()
                    .setDeviceName(buffer != null ? buffer.toString() : "")
                    .setVendorId(vendorId)
                    .setProductId(productId);

            /* Add Device to list */
            devices.add(builder.build());
        }
        return devices;
    }
}
