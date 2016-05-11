package com.example.omni_vision;

import android.hardware.Camera;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.omni_vision.Unused.YouTubeFragment;


/**
 * TODO:
 *
 * ++ Obfusicate API Key from Cofig class.
 *
 * +) Move declarations and instantiations of fragments up to improve performance.
 * 1) Set animations for main buttons - change "Web" and "Media" to "<--" and "-->".
 * 2) Set haptic feedback for buttons (will work only on some devices).
 */


public class MainActivity extends AppCompatActivity {

    /**
     * Camera related declarations.
     */
    private SurfaceView preview = null;
    private SurfaceHolder previewHolder = null;
    private Camera camera = null;
    private boolean inPreview = false;
    private boolean cameraConfigured = false;

    /**
     * Main menu button declarations.
     */
    Button leftMainButton;
    Button wikiPediaButton;
    Button gmailButton;
    Button rightMainButton;
    Button youTubeButton;
    Button mapsButton;
    Button customButton;
    Button urlSubmittButton;
    EditText customWebViewEditText;
    Button slackButton;
    Button slackSubmittButton;
    EditText slackEditText;
    boolean leftMainMenuNotShowing = true;
    boolean rightMainMenuNotShowing = true;
    boolean wikiPediaFragmentNotShowing = true;
    boolean gmailFragmentNotShowing = true;
    boolean customFragmentNotShowing = true;
    boolean youtubeFragmentNotShowing = true;
    boolean slackFragmentNotShowing = true;
    boolean mapsFragmentNotShowing = true;
    FrameLayout leftFrameLayout, rightFrameLayout;

    SlackFragment slackFragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Setting up the camera.
         */
        preview = (SurfaceView) findViewById(R.id.surfaceview);
//        preview.setZOrderMediaOverlay(false);
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        camera = Camera.open();

        /**
         * Getting the main menu button on top (in front of) camera preview
         */
        leftMainButton = (Button) findViewById(R.id.leftMainButton);
        rightMainButton = (Button) findViewById(R.id.rightMainButton);
        leftMainButton.bringToFront();
        preview.invalidate();

        /**
         * Instantiating and setting up main menu buttons.
         */
        wikiPediaButton = (Button) findViewById(R.id.wikiPediaButton);
        wikiPediaButton.setVisibility(View.GONE);
        gmailButton = (Button) findViewById(R.id.gmailButton);
        gmailButton.setVisibility(View.GONE);
        youTubeButton = (Button) findViewById(R.id.youTubeButton);
        youTubeButton.setVisibility(View.GONE);
        mapsButton = (Button) findViewById(R.id.mapsButton);
        mapsButton.setVisibility(View.GONE);
        customButton = (Button) findViewById(R.id.customButton);
        customButton.setVisibility(View.GONE);
        customWebViewEditText = (EditText) findViewById(R.id.customWebViewEditText);
        customWebViewEditText.setVisibility(View.GONE);
        urlSubmittButton = (Button) findViewById(R.id.urlSubmittButton);
        urlSubmittButton.setVisibility(View.GONE);
        slackButton = (Button) findViewById(R.id.slackButton);
        slackButton.setVisibility(View.GONE);
        slackSubmittButton = (Button) findViewById(R.id.slackSubmittButton);
        slackSubmittButton.setVisibility(View.GONE);
        slackEditText = (EditText) findViewById(R.id.slackEditText);
        slackEditText.setVisibility(View.GONE);


        /**
         *  Setting up FrameLayouts
         */
        leftFrameLayout = (FrameLayout) findViewById(R.id.leftFrameLayout);
        rightFrameLayout = (FrameLayout) findViewById(R.id.rightFrameLayout);
        rightFrameLayout.bringToFront();

        leftFrameLayout.setVisibility(View.GONE);
        rightFrameLayout.setVisibility(View.GONE);


        /**
         * Left sub-Main Button click-listeners and behaviors
         */
        wikiPediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wikiPediaFragmentNotShowing) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    WikiPediaWebViewFragment fragment = new WikiPediaWebViewFragment();
                    fragmentTransaction.replace(R.id.leftFrameLayout, fragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT);
                    toast.show();
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmittButton.setVisibility(View.GONE);
                    wikiPediaButton.animate().rotation(360);
                    wikiPediaFragmentNotShowing = false;
                } else if(!wikiPediaFragmentNotShowing){
                    leftFrameLayout.setVisibility(View.GONE);
                    wikiPediaButton.animate().rotation(720);
                    wikiPediaFragmentNotShowing = true;


                }
            }
        });

        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gmailFragmentNotShowing) {
                    android.support.v4.app.FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
                    GmailWebViewFragment fragment = new GmailWebViewFragment();
                    fragmentTransaction.replace(R.id.leftFrameLayout, fragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT);
                    toast.show();
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmittButton.setVisibility(View.GONE);
                    gmailButton.animate().rotation(360);
                    gmailFragmentNotShowing = false;
                } else if(!gmailFragmentNotShowing){
                    leftFrameLayout.setVisibility(View.GONE);
                    gmailButton.animate().rotation(720);
                    gmailFragmentNotShowing = true;
                }
            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customFragmentNotShowing) {
                    urlSubmittButton.setVisibility(View.VISIBLE);
                    customWebViewEditText.setVisibility(View.VISIBLE);
                    CustomWebViewFragment customWebViewFragment = new CustomWebViewFragment();
                    android.support.v4.app.FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager3.beginTransaction();
                    Log.i("MainActivity", "EditText is" + customWebViewEditText.getText().toString());
                    fragmentTransaction.replace(R.id.leftFrameLayout, customWebViewFragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    customButton.animate().rotation(360);
                    customFragmentNotShowing = false;
                } else if(!customFragmentNotShowing){
                    urlSubmittButton.setVisibility(View.GONE);
                    customWebViewEditText.setVisibility(View.GONE);
                    leftFrameLayout.setVisibility(View.GONE);
                    customButton.animate().rotation(720);
                    customFragmentNotShowing = true;
                }
            }
        });

        urlSubmittButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomWebViewFragment customWebViewFragment = new CustomWebViewFragment();
                android.support.v4.app.FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager3.beginTransaction();
                fragmentTransaction.replace(R.id.leftFrameLayout, customWebViewFragment);
                fragmentTransaction.commit();
                customWebViewFragment.setCustomURL(customWebViewEditText.getText().toString());
                leftFrameLayout.setVisibility(View.VISIBLE);
                urlSubmittButton.animate().rotation(360);
                Toast toast = Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        /**
         * Right sub-Main Button click-listeners and behaviors -------------------------------------
         */

        youTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(youtubeFragmentNotShowing) {

                    android.support.v4.app.FragmentManager fragmentManager4 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager4.beginTransaction();
                    YouTubeWebView fragment = new YouTubeWebView();
                    fragmentTransaction.replace(R.id.rightFrameLayout, fragment);
                    fragmentTransaction.commit();
                    rightFrameLayout.setVisibility(View.VISIBLE);
//
//                    YouTubeFragment fragment = new YouTubeFragment();
//                    Bundle arbBundle = new Bundle();
//                    arbBundle.putString("VIDEO_URL", "YRrjDTdq8MA");
//                    fragment.setArguments(arbBundle);
//                    android.support.v4.app.FragmentManager fragmentManager4 = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager4.beginTransaction();
//
//                    fragmentTransaction.add(R.id.rightFrameLayout, fragment);
//                    fragmentTransaction.commit();
//                    rightFrameLayout.setVisibility(View.VISIBLE);
//                    preview.setVisibility(View.GONE);
                   // startActivity(YouTubeStandalonePlayer.createVideoIntent(MainActivity.this, "DEVELOPER_KEY", "YRrjDTdq8MA", 0, true, true));

                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                    youTubeButton.animate().rotation(360);
                    youtubeFragmentNotShowing = false;
                } else if(!youtubeFragmentNotShowing){
                    rightFrameLayout.setVisibility(View.GONE);
                    youTubeButton.animate().rotation(720);
                    youtubeFragmentNotShowing = true;
                }
            }
        });

        slackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slackFragmentNotShowing){
                    rightFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Enter a message to post.", Toast.LENGTH_SHORT).show();
                    slackFragment = new SlackFragment();
                    android.support.v4.app.FragmentManager fragmentManager5 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager5.beginTransaction();
                    fragmentTransaction.replace(R.id.rightFrameLayout, slackFragment);
                    fragmentTransaction.commit();
                    slackButton.animate().rotation(360);
                    slackEditText.setVisibility(View.VISIBLE);
                    slackSubmittButton.setVisibility(View.VISIBLE);
                    slackFragmentNotShowing = false;
                } else if(!slackFragmentNotShowing){
                    rightFrameLayout.setVisibility(View.GONE);
                    slackEditText.setVisibility(View.GONE);
                    slackSubmittButton.setVisibility(View.GONE);
                    slackButton.animate().rotation(720);
                    slackFragmentNotShowing = true;
                }
            }
        });

        slackSubmittButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slackFragment.setMyMessage(slackEditText.getText().toString());
                slackSubmittButton.animate().rotation(360);
                slackEditText.getText().clear();
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapsFragmentNotShowing){
                    Toast.makeText(MainActivity.this, "Coming Soon.", Toast.LENGTH_SHORT).show();
                    mapsButton.animate().rotation(360);
                    rightFrameLayout.setVisibility(View.GONE);
                    mapsFragmentNotShowing = false;
                } else if(!mapsFragmentNotShowing){
                    mapsButton.animate().rotation(720);
                    mapsFragmentNotShowing = true;
                }
            }
        });


        /**
         * Main menu clickListeners and behaviors. -------------------------------------------------
         */
        leftMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftMainMenuNotShowing){
                    wikiPediaButton.setVisibility(View.VISIBLE);
                    gmailButton.setVisibility(View.VISIBLE);
                    customButton.setVisibility(View.VISIBLE);
                    leftMainButton.setHapticFeedbackEnabled(true);
                    leftMainButton.animate().rotation(360);
                    leftMainMenuNotShowing = false;
                }
                else if (!leftMainMenuNotShowing){
                    wikiPediaButton.setVisibility(View.GONE);
                    gmailButton.setVisibility(View.GONE);
                    customButton.setVisibility(View.GONE);
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmittButton.setVisibility(View.GONE);
                    leftMainButton.animate().rotation(720);
                    leftMainMenuNotShowing = true;
                }
            }
        });

        rightMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightMainMenuNotShowing){
                    youTubeButton.setVisibility(View.VISIBLE);
                    mapsButton.setVisibility(View.VISIBLE);
                    slackButton.setVisibility(View.VISIBLE);
                    rightMainButton.animate().rotation(360);
                    rightMainMenuNotShowing = false;
                }
                else if (!rightMainMenuNotShowing){
                    youTubeButton.setVisibility(View.GONE);
                    mapsButton.setVisibility(View.GONE);
                    slackButton.setVisibility(View.GONE);
                    slackEditText.setVisibility(View.GONE);
                    slackSubmittButton.setVisibility(View.GONE);
                    rightMainButton.animate().rotation(720);
                    rightMainMenuNotShowing = true;
                }
            }
        });

    }

    /**
     * ----------------End OnCreate-----------------------------------------------------------------
     *
     * ----------------Start Methods----------------------------------------------------------------
     */

    @Override
    public void onBackPressed() {
        if (!wikiPediaFragmentNotShowing && WikiPediaWebViewFragment.myWebView.canGoBack()) {
            WikiPediaWebViewFragment.myWebView.goBack();
            Log.i("Main Activity", "Back button worked for Wiki");
        } else if (!customFragmentNotShowing && CustomWebViewFragment.myWebView.canGoBack()) {
            CustomWebViewFragment.myWebView.goBack();
            Log.i("Main Activity", "Back button worked for Custom");
        } else if (!gmailFragmentNotShowing && GmailWebViewFragment.myWebView.canGoBack()) {
             GmailWebViewFragment.myWebView.goBack();
        } else if (!youtubeFragmentNotShowing && YouTubeWebView.myWebView.canGoBack()) {
            YouTubeWebView.myWebView.goBack();
        }
        else {
            Log.i("Main Activity", "Non of the back buttons worked");
            super.onBackPressed();
        }
    }


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
        camera = null;
        inPreview = false;

        super.onPause();
    }

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    /**
     * Hides the Status bar and soft keys.
     * Causes non-fatal errors.
     * @param hasFocus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * Methods related to camera functionality.
     * @param width
     * @param height
     * @param parameters
     * @return
     */

    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;

                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }
        return (result);
    }

    private void initPreview(int width, int height) {
        if (camera != null && previewHolder.getSurface() != null) {
            try {
                camera.setPreviewDisplay(previewHolder);
            } catch (Throwable t) {
                Log.e("MainActivity",
                        "Exception in setPreviewDisplay()", t);
                Toast
                        .makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
            if (!cameraConfigured) {
                Camera.Parameters parameters = camera.getParameters();
                Camera.Size size = getBestPreviewSize(width, height,
                        parameters);
                if (size != null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    cameraConfigured = true;
                }
            }
        }
    }

    private void startPreview() {
        if (cameraConfigured && camera != null) {
            camera.startPreview();
            inPreview = true;
        }
    }

    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
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

}
