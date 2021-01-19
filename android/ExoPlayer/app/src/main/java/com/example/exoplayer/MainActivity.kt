package com.example.exoplayer

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util


class MainActivity : AppCompatActivity() {
    val TAG = "ExoPlayer"
    var playerView: PlayerView? = null
    var player: SimpleExoPlayer? = null
    var playWhenReady: Boolean = true
    var currentWindow: Int = 0
    var playbackPosition: Long = 0L
    var fullscreenButton: ImageView? = null
    var fullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //playerView = findViewById(R.id.exoPlayerView)
        playerView = binding.exoPlayerView

        Log.d(TAG, "onCreate: ${playerView}")
    }

    private fun initializePlayer() {
        Log.d(TAG, "initializePlayer: ")

        //플레이어 생성
        if( player==null ) {
             player = SimpleExoPlayer.Builder(this).build()
        }

        //뷰에 플레이어 연결
        playerView?.setPlayer(player)

        //플레이어에 영상 연결
        val mediaItem:MediaItem = MediaItem.fromUri(getString(R.string.url_m3u8_01))
        player!!.setMediaItem(mediaItem)

        player!!.setPlayWhenReady(playWhenReady);
        player!!.seekTo(currentWindow, playbackPosition);
        player!!.prepare();
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT < 24 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")

        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")

        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private fun hideSystemUi() {
        Log.d(TAG, "hideSystemUi: ")

        playerView!!.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)
        /*
        RESIZE_MODE_FIT
        RESIZE_MODE_FIXED_WIDTH
        RESIZE_MODE_FIXED_HEIGHT
        RESIZE_MODE_FILL
        RESIZE_MODE_ZOOM
        */

        /*
        playerView!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        */
    }


    private fun releasePlayer() {
        Log.d(TAG, "releasePlayer: ")

        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }
    }
}