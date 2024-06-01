package com.aminhosseintehrani.roomtemperature;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class ArduinoCommunicator {
    InputStream inputStream;
    BluetoothSocket bluetoothSocket;
    public ArduinoCommunicator(){



    }



   public  void sendInformation(){


    }

    public void readData(Context context){
        try {
            inputStream = bluetoothSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytes;

            // Read from the InputStream
            bytes = inputStream.read(buffer);

            // Convert the bytes to a string
            String data = new String(buffer, 0, bytes);

            // Process the received data
            // For example, display it in a TextView
           // textView.setText("Received: " + data);
            Toast.makeText(context,data,Toast.LENGTH_LONG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
