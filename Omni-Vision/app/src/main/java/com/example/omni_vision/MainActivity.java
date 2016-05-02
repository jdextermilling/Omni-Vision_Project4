package com.example.omni_vision;

import android.app.Activity;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


//                                        implepments SurfaceViewHolder.Callback
public class MainActivity extends AppCompatActivity{

    private SurfaceView preview=null;
    private SurfaceHolder previewHolder=null;
    private Camera camera=null;
    private boolean inPreview=false;
    private boolean cameraConfigured=false;


// ------first try with camera --------------
//    SurfaceView surfaceView;
//    SurfaceHolder surfaceHolder;
//    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preview=(SurfaceView)findViewById(R.id.surfaceview);
        previewHolder=preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        camera = Camera.open();

//---------------first try with the camera ------------------------------------------------------
//        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
//        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.addCallback(this);
//
//        surfaceCreated(surfaceHolder);
//        refreshCamera();
//        surfaceChanged(SurfaceHolder holder, int format, int w, int h);
    }

    /**
     *----------------End OnCreate-----------------------------------------------------------------
     *
     *----------------Start Methods----------------------------------------------------------------
     */


    @Override
    public void onResume() {
        super.onResume();

        releaseCameraAndPreview();
        camera = Camera.open();
        startPreview();
    }

    @Override
    public void onPause() {
        if (inPreview) {
            camera.stopPreview();
        }

        camera.release();
        camera=null;
        inPreview=false;

        super.onPause();
    }

    private void releaseCameraAndPreview() {
        //preview.setCamera(null);
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private Camera.Size getBestPreviewSize(int width, int height,
                                           Camera.Parameters parameters) {
        Camera.Size result=null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                }
                else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }

        return(result);
    }

    private void initPreview(int width, int height) {
        if (camera!=null && previewHolder.getSurface()!=null) {
            try {
                camera.setPreviewDisplay(previewHolder);
            }
            catch (Throwable t) {
                Log.e("MainActivity",
                        "Exception in setPreviewDisplay()", t);
                Toast
                        .makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }

            if (!cameraConfigured) {
                Camera.Parameters parameters=camera.getParameters();
                Camera.Size size=getBestPreviewSize(width, height,
                        parameters);

                if (size!=null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    cameraConfigured=true;
                }
            }
        }
    }

    private void startPreview() {
        if (cameraConfigured && camera!=null) {
            camera.startPreview();
            inPreview=true;
        }
    }

    SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            // no-op -- wait until surfaceChanged()
        }

        public void surfaceChanged(SurfaceHolder holder,
                                   int format, int width,
                                   int height) {
            initPreview(width, height);
            startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // no-op
        }
    };


// --------- first try with camera --------------------------------------------------------------

//    public void refreshCamera() {
//        if (surfaceHolder.getSurface() == null) {
//            // preview surface does not exist
//            return;
//        }
//        // stop preview before making changes
//        try {
//            camera.stopPreview();
//        } catch (Exception e) {
//            // ignore: tried to stop a non-existent preview
//        }
//        // set preview size and make any resize, rotate or
//        // reformatting changes here
//        // start preview with new settings
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//            camera.startPreview();
//        } catch (Exception e) {
//        }
//    }
//
//    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
//        // Now that the size is known, set up the camera parameters and begin
//        // the preview.
//        refreshCamera();
//    }
//
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            // open the camera
//            camera = Camera.open();
//        } catch (RuntimeException e) {
//            // check for exceptions
//            System.err.println(e);
//            return;
//
//        }
//        Camera.Parameters param;
//        param = camera.getParameters();
//        // modify parameter
//        param.setPreviewSize(352, 288);
//        camera.setParameters(param);
//        try {
//            // The Surface has been created, now tell the camera where to draw
//            // the preview.
//            camera.setPreviewDisplay(surfaceHolder);
//            camera.startPreview();
//        } catch (Exception e) {
//            // check for exceptions
//            System.err.println(e);
//            return;
//        }
//    }
//
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        // stop preview and release camera
//        camera.stopPreview();
//        camera.release();
//        camera = null;
//    }



}
