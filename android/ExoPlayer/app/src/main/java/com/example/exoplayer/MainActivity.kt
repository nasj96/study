package com.example.exoplayer

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util


class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = "ExoPlayer"
    }

    var binding: ActivityMainBinding? = null
    var fullscreenButton: ImageView? = null
    var playerView: PlayerView? = null
    var player: SimpleExoPlayer? = null
    var playWhenReady: Boolean = true
    var currentWindow: Int = 0
    var playbackPosition: Long = 0L
    var fullscreen = false
    private var playbackStateListener: PlaybackStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        playerView = binding!!.exoPlayerView
        fullscreenButton = playerView!!.findViewById(R.id.exo_fullscreen_icon)

        playbackStateListener = PlaybackStateListener()
    }

    private class PlaybackStateListener : Player.EventListener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            var stateString: String

            stateString = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED -"
                else -> "UNKNOWN_STATE -"
            }

            Log.d(TAG, "onPlaybackStateChanged: $stateString")
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            Log.d(TAG, "onIsPlayingChanged: $isPlaying")
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            super.onPlayerError(error)

            when (error.type) {
                ExoPlaybackException.TYPE_SOURCE -> Log.e(
                    TAG,
                    "onPlayerError, TYPE_SOURCE: " + error.sourceException.toString()
                )
                ExoPlaybackException.TYPE_RENDERER -> Log.e(
                    TAG,
                    "onPlayerError, TYPE_RENDERER: " + error.rendererException.toString()
                )
                ExoPlaybackException.TYPE_UNEXPECTED -> Log.e(
                    TAG,
                    "onPlayerError, TYPE_UNEXPECTED: " + error.unexpectedException.toString()
                )
                else -> Log.e(
                    TAG,
                    "onPlayerError, ETC: " + error.sourceException.toString()
                )
            }

        }

        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            Log.d(TAG, "onIsLoadingChanged: $isLoading")
        }

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
            super.onPlaybackParametersChanged(playbackParameters)
            Log.d(TAG, "onPlaybackParametersChanged: $playbackParameters")
        }
    }

    private fun initializePlayer() {
        Log.d(TAG, "initializePlayer: ")


        //플레이어 생성
        if (player == null) {
            player = SimpleExoPlayer.Builder(this).build()
        }

        //뷰에 플레이어 연결
        playerView?.setPlayer(player)

        //플레이어에 영상 연결
        val mediaItem: MediaItem = MediaItem.fromUri(getString(R.string.url_m3u8_01))
        player!!.setMediaItem(mediaItem)

        //playlist인경우
        //val secondMediaItem = MediaItem.fromUri(getString(R.string.url_m3u8_02))
        //player!!.addMediaItem(secondMediaItem)

        player!!.setPlayWhenReady(playWhenReady);
        player!!.seekTo(currentWindow, playbackPosition);
        playbackStateListener?.let { player!!.addListener(it) };
        player!!.prepare();

        setFullscreenAction();
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
            playbackStateListener?.let { player!!.removeListener(it) };
            player!!.release()
            player = null
        }
    }

    fun setFullscreenAction() {
        Log.d(TAG, "setFullscreenAction: ")

        if (fullscreenButton != null) {

            fullscreenButton!!.setOnClickListener {
                Log.d(TAG, "fullscreen setOnClickListener: $fullscreen")

                if (fullscreen) {
                    fullscreenButton!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.ic_fullscreen_open
                        )
                    )

//                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

                    if (supportActionBar != null) {
                        supportActionBar!!.show()
                    }

                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

//                    val params = playerView!!.layoutParams
//                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
//                    params.height =
//                        (200 * applicationContext.resources.displayMetrics.density).toInt()
//                    playerView!!.layoutParams = params

                    fullscreen = false
                } else {
                    fullscreenButton!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.ic_fullscreen_close
                        )
                    )

//                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

                    if (supportActionBar != null) {
                        supportActionBar!!.hide()
                    }

                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

//                    var params = playerView!!.layoutParams
//                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    params.height = ViewGroup.LayoutParams.MATCH_PARENT
//                    playerView!!.layoutParams = params

                    fullscreen = true
                }
            }
        }
    }
}
