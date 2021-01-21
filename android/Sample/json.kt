import android.util.Log
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "Logs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val category = mutableMapOf<String, String>()
        category.put("cate01", "value01")
        category.put("cate02", "value02")

        val iteminfo = mutableMapOf<String, String>()
        iteminfo.put("product_id", "123456789")
        iteminfo.put("product_code", "111")

        val position = "pos_1"
        val logType = "show"
        val keyword = ""
        val currentDateTime = Calendar.getInstance().time
        val time = SimpleDateFormat("yyyy-MM-DD HH:mm:ss", Locale.KOREA).format(currentDateTime)
        //Log.d(TAG, "$time: $time ")

        val arrData = mutableMapOf<String, Any?>()
        arrData.put("category", category)
        arrData.put("position", position)
        arrData.put("iteminfo", iteminfo)
        arrData.put("logType", logType)
        arrData.put("keyword", keyword)
        arrData.put("time", time)

        var data = mutableListOf<Any?>()
        data.add(arrData)
        data.add(arrData)
        data.add(arrData)

        val json = Gson().toJson(data).toString()
        var simpleTYpe = json::class.simpleName
        Log.d(TAG, "json: \n${simpleTYpe}\n${json}")

    }
    
}

