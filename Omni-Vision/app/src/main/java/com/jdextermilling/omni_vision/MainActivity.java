package com.jdextermilling.omni_vision;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


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
    Button urlSubmitButton;
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

    /**
     * Setting the first instances of fragments.
     */
    WikiPediaWebViewFragment wikiPediaWebViewFragment = new WikiPediaWebViewFragment();
    SlackFragment slackFragment = new SlackFragment();
    GmailWebViewFragment gmailWebViewFragment = new GmailWebViewFragment();
    CustomWebViewFragment customWebViewFragment = new CustomWebViewFragment();
    YouTubeWebViewFragment youTubeWebView = new YouTubeWebViewFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Setting up the camera.
         */
        preview = (SurfaceView) findViewById(R.id.surfaceview);
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
        urlSubmitButton = (Button) findViewById(R.id.urlSubmittButton);
        urlSubmitButton.setVisibility(View.GONE);
        slackButton = (Button) findViewById(R.id.slackButton);
        slackButton.setVisibility(View.GONE);
        slackSubmittButton = (Button) findViewById(R.id.slackSubmittButton);
        slackSubmittButton.setVisibility(View.GONE);
        slackEditText = (EditText) findViewById(R.id.slackEditText);
        slackEditText.setVisibility(View.GONE);


        /**
         * Setting up notifications(Status bar icon)
         */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.small_eye)
                .setContentTitle("Omni-Vision")
                .setContentText("Click to return to app");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());


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
                if (wikiPediaFragmentNotShowing) {
                    if (wikiPediaWebViewFragment == null) {
                        wikiPediaWebViewFragment = new WikiPediaWebViewFragment();
                    }
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.leftFrameLayout, wikiPediaWebViewFragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmitButton.setVisibility(View.GONE);
                    wikiPediaButton.animate().rotation(360);
                    wikiPediaFragmentNotShowing = false;
                } else if (!wikiPediaFragmentNotShowing) {
                    leftFrameLayout.setVisibility(View.GONE);
                    wikiPediaButton.animate().rotation(720);
                    wikiPediaFragmentNotShowing = true;
                }
            }
        });

        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gmailFragmentNotShowing) {
                    if (gmailWebViewFragment == null) {
                        gmailWebViewFragment = new GmailWebViewFragment();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.leftFrameLayout, gmailWebViewFragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmitButton.setVisibility(View.GONE);
                    gmailButton.animate().rotation(360);
                    gmailFragmentNotShowing = false;
                } else if (!gmailFragmentNotShowing) {
                    leftFrameLayout.setVisibility(View.GONE);
                    gmailButton.animate().rotation(720);
                    gmailFragmentNotShowing = true;
                }
            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customFragmentNotShowing) {
                    if(customWebViewFragment == null){
                        customWebViewFragment = new CustomWebViewFragment();
                    }
                    urlSubmitButton.setVisibility(View.VISIBLE);
                    customWebViewEditText.setVisibility(View.VISIBLE);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.leftFrameLayout, customWebViewFragment);
                    fragmentTransaction.commit();
                    leftFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                    customButton.animate().rotation(360);
                    customFragmentNotShowing = false;
                } else if (!customFragmentNotShowing) {
                    urlSubmitButton.setVisibility(View.GONE);
                    customWebViewEditText.setVisibility(View.GONE);
                    leftFrameLayout.setVisibility(View.GONE);
                    customButton.animate().rotation(720);
                    customFragmentNotShowing = true;
                }
            }
        });

        urlSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customWebViewFragment.loadNewUrl(customWebViewEditText.getText().toString());
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.leftFrameLayout, customWebViewFragment);
                fragmentTransaction.commit();
                leftFrameLayout.setVisibility(View.VISIBLE);
                urlSubmitButton.animate().rotation(360);
                Toast toast = Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        /**
         * Right sub-Main Button click-listeners and behaviors -------------------------------------
         */

        youTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtubeFragmentNotShowing) {
                    if (youTubeWebView == null) {
                        youTubeWebView = new YouTubeWebViewFragment();
                    }
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rightFrameLayout, youTubeWebView);
                    fragmentTransaction.commit();
                    rightFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                    youTubeButton.animate().rotation(360);
                    youtubeFragmentNotShowing = false;
                } else if (!youtubeFragmentNotShowing) {
                    rightFrameLayout.setVisibility(View.GONE);
                    youTubeButton.animate().rotation(720);
                    youtubeFragmentNotShowing = true;
                }
            }
        });

        slackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slackFragmentNotShowing) {
                    if (slackFragment == null) {
                        slackFragment = new SlackFragment();
                    }
                    rightFrameLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Enter a message to post.", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rightFrameLayout, slackFragment);
                    fragmentTransaction.commit();
                    slackButton.animate().rotation(360);
                    slackEditText.setVisibility(View.VISIBLE);
                    slackSubmittButton.setVisibility(View.VISIBLE);
                    slackFragmentNotShowing = false;
                } else if (!slackFragmentNotShowing) {
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
                if (mapsFragmentNotShowing) {
                    Toast.makeText(MainActivity.this, "Coming Soon.", Toast.LENGTH_SHORT).show();
                    mapsButton.animate().rotation(360);
                    rightFrameLayout.setVisibility(View.GONE);
                    mapsFragmentNotShowing = false;
                } else if (!mapsFragmentNotShowing) {
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
                if (leftMainMenuNotShowing) {
                    wikiPediaButton.setVisibility(View.VISIBLE);
                    gmailButton.setVisibility(View.VISIBLE);
                    customButton.setVisibility(View.VISIBLE);
                    leftMainButton.setHapticFeedbackEnabled(true);
                    leftMainButton.animate().rotation(360);
                    leftMainMenuNotShowing = false;
                } else if (!leftMainMenuNotShowing) {
                    wikiPediaButton.setVisibility(View.GONE);
                    gmailButton.setVisibility(View.GONE);
                    customButton.setVisibility(View.GONE);
                    customWebViewEditText.setVisibility(View.GONE);
                    urlSubmitButton.setVisibility(View.GONE);
                    leftMainButton.animate().rotation(720);
                    leftMainMenuNotShowing = true;
                }
            }
        });

        rightMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightMainMenuNotShowing) {
                    youTubeButton.setVisibility(View.VISIBLE);
                    mapsButton.setVisibility(View.VISIBLE);
                    slackButton.setVisibility(View.VISIBLE);
                    rightMainButton.animate().rotation(360);
                    rightMainMenuNotShowing = false;
                } else if (!rightMainMenuNotShowing) {
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
     * <p/>
     * ----------------Start Methods----------------------------------------------------------------
     */

    @Override
    public void onBackPressed() {
        if (!wikiPediaFragmentNotShowing && wikiPediaWebViewFragment.webviewCanGoBack()) {
            wikiPediaWebViewFragment.webviewGoBack();
        } else if (!customFragmentNotShowing && customWebViewFragment.webviewCanGoBack()) {
            customWebViewFragment.webviewGoBack();
        } else if (!gmailFragmentNotShowing && gmailWebViewFragment.webviewCanGoBack()) {
            gmailWebViewFragment.webviewGoBack();
        } else if (!youtubeFragmentNotShowing && youTubeWebView.webviewCanGoBack()) {
            youTubeWebView.webviewGoBack();
        } else {
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
     *
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
     *
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
        }

        public void surfaceChanged(SurfaceHolder holder,
                                   int format, int width,
                                   int height) {
            initPreview(width, height);
            startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    };

}
