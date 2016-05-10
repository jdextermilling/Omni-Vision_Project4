package com.example.omni_vision.Unused;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * Created by JacobDexter-Milling on 5/9/16.
 */
public class YouTubeFragment extends YouTubePlayerSupportFragment implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = YouTubeFragment.class.getSimpleName();
    private static final String DEVELOPER_KEY = "";

    YouTubePlayer player;
    YouTubePlayerView youtubeView;

    Boolean wasRestored = false;
    //    YouTubePlayer player;
    String url;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.initialize(DEVELOPER_KEY, this);
        url = getArguments().getString("VIDEO_URL");
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//
////        View fragmentYoutubeView = inflater.inflate(R.layout.youtube_fragment, container, false);
//////        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
////        FragmentManager fragmentManager = getFragmentManager();
////        youTubePlayerSupportFragment =(YouTubePlayerSupportFragment) fragmentManager.findFragmentById(R.id.youtube_view);
////        youTubePlayerSupportFragment.initialize(DEVELOPER_KEY, this);
//
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.rightFrameLayout, youTubePlayerSupportFragment);
////        fragmentTransaction.commit();
//
//        return player;
//
//
//    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.youtubeView = (YouTubePlayerView)view;

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initialize(String s, YouTubePlayer.OnInitializedListener onInitializedListener) {
        super.initialize(s, onInitializedListener);
        Log.i(TAG, "Initializing...");

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.i("Youtube Fragment", "WE DID IT BOYS");
        this.player = youTubePlayer;
        if (!wasRestored) {
            youTubePlayer.loadVideo(url);

            Log.i(TAG, String.format("Child count: %d", youtubeView.getChildCount()));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        if (result.isUserRecoverableError()) {
//            result.getErrorDialog(this.getActivity(),1).show();
//        } else {
//            Toast.makeText(this.getActivity(),
//                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
//                    Toast.LENGTH_LONG).show();
//        }
    }


    public YouTubePlayer getPlayer() {
        return player;
    }
}