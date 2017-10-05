package com.example.blurryface.cameraapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class CameraAppActivity extends AppCompatActivity {
    Button recordButton;
    //variable of the request code
    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_app);
        recordButton = (Button)findViewById(R.id.recordButton);
        //if the device doesnt have a camera disable the button
        if(!hasCamera())
            recordButton.setEnabled(false);

    }
    //checking if the hardware device has a camera
    private boolean hasCamera()
    {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT))
            return true;
        else
            return false;
    }
    public void startRecording(View view)
    {
        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myvideo.mp4");
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Uri.fromFile(mediaFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,VIDEO_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case VIDEO_CAPTURE:
                //check if the activity ran
                if(requestCode==RESULT_OK)
                    Toast.makeText(this,"Video has been save to \n"+fileUri,Toast.LENGTH_LONG).show();
                else if (requestCode==RESULT_CANCELED)
                    Toast.makeText(this,"Video Recording is cancelled",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this,"Failed to record Video",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
