package com.aminhosseintehrani.roomtemperature;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import me.aflak.bluetooth.reader.SocketReader;

public class BluetoothController {
    ArrayList<BluetoothDeviceProfile> devices;
public BluetoothController(){
    devices = new ArrayList<>();
}
    public ArrayList<BluetoothDeviceProfile> getAllPairedDevices() {

return devices;
    }

    public void read(){

    }

    public void addPairedDevicesAddressAndName(java.util.Set<android.bluetooth.BluetoothDevice> pairedDevices, Context context) {


        if (pairedDevices.size() > 0) {

            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address



                    devices.add(new BluetoothDeviceProfile(deviceName, deviceHardwareAddress));

                }

            }


        }

    }
}





