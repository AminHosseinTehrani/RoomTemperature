package com.aminhosseintehrani.roomtemperature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.BluetoothCallback;
import me.aflak.bluetooth.interfaces.DeviceCallback;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;
import java.util.UUID;

import android.Manifest;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    BluetoothController bluetoothController;
   Bluetooth bluetooth;

   private boolean firstMessage = false;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }



    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.BLUETOOTH_SCAN};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Permission need :)",
                    1, perms);
        }
    }

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;

    @Override
    protected void onStart() {
        super.onStart();



    }
    private BluetoothCallback bluetoothCallback = new BluetoothCallback() {
        @Override public void onBluetoothTurningOn() {}
        @Override public void onBluetoothTurningOff() {}
        @Override public void onBluetoothOff() {}

        @Override
        public void onBluetoothOn() {


        }

        @Override
        public void onUserDeniedActivation() {
            // handle activation denial...
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        TextView temperatureText = findViewById(R.id.temperaturetxt);
        TextView disconnectedText = findViewById(R.id.disconnectedtxt);
        ProgressBar messageBar = findViewById(R.id.progressbar);

        bluetooth = new Bluetooth(this,UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
        bluetooth.setBluetoothCallback(bluetoothCallback);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            bluetooth.onStart();


            if(bluetooth.isEnabled()){
                // doStuffWhenBluetoothOn() ...

                bluetooth.connectToAddressWithPortTrick("00:14:03:05:09:88");


            } else {
                bluetooth.enable();

            }

        }



        bluetooth.setDeviceCallback(new DeviceCallback() {
            @Override public void onDeviceConnected(BluetoothDevice device) {
                runOnUiThread(() -> {

                        final Toast toast = Toast.makeText(MainActivity.this, "Device connected ", Toast.LENGTH_LONG);
                        toast.show();
                    disconnectedText.setVisibility(View.INVISIBLE);
                });
            }
            @Override public void onDeviceDisconnected(BluetoothDevice device, String message) {
                runOnUiThread(() -> {
                disconnectedText.setVisibility(View.VISIBLE);
                });
            }
            @Override public void onMessage(byte[] message) {


                messageBar.setVisibility(View.INVISIBLE);



                String receivedMessage = new String(message);
                Log.d("Bluetooth", "Received message: " );

                runOnUiThread(() -> {

                    temperatureText.setText(receivedMessage +"°C");

                });

            }

            @Override
            public void onError(int errorCode) {
                Toast.makeText(MainActivity.this,errorCode,Toast.LENGTH_LONG).show();
            }


            @Override public void onConnectError(BluetoothDevice device, String message) {

                runOnUiThread(() -> {

                    final Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
                    toast.show();

                });
            }
        });






        methodRequiresTwoPermission();

//        Toast.makeText(MainActivity.this,String.valueOf(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)  == PackageManager.PERMISSION_GRANTED) + "equal",Toast.LENGTH_LONG).show();







    }



    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
    }


}