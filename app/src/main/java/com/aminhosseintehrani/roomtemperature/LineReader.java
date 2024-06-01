package com.aminhosseintehrani.roomtemperature;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.aflak.bluetooth.reader.SocketReader;

public class LineReader extends SocketReader {
    private BufferedReader reader;
    private Context context;

    public LineReader(InputStream inputStream, Context context) {
        super(inputStream);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        this.context = context;
    }

    @Override
    public byte[] read() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            // Display received content in a Toast
            showToast(line);
            return line.getBytes();
        }
        return null;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}