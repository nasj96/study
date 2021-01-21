
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private val TAG = "Logs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Image Load
        val url = "http://laza.jalbum.net/Testing%20Base%20as%20site/Media/slides/big_buck_bunny.jpg"
        val imageView = findViewById<View>(R.id.iv_image) as ImageView

        imageView.loadUrl(url) //Glide.with(this).load(url).into(imageView)
    }

    fun ImageView.loadUrl(imageUrl: String?) {
        Glide.with(this).load(imageUrl).into(this)
    }
}

