package spacekotlin.vaniukova.imageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import spacekotlin.vaniukova.imageview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDownload.setOnClickListener {
            if (binding.editTextLink.text.isEmpty()){
                Toast.makeText(applicationContext, "Введите ссылку", Toast.LENGTH_SHORT).show()
            }else{
                val url = binding.editTextLink.text.toString()
                downloadImage(url)
            }
        }
    }

    private fun downloadImage(url:String){
        if ("https://" in url) {
            Glide
                .with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.imageView)
            binding.imageView.visibility = View.VISIBLE
        } else {
            Toast.makeText(this, "Была введена не ссылка", Toast.LENGTH_SHORT).show()
        }
    }
}