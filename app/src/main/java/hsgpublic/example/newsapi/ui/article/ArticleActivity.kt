package hsgpublic.example.newsapi.ui.article

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hsgpublic.example.newsapi.R
import hsgpublic.example.newsapi.databinding.ArticleActivityBinding

class ArticleActivity: AppCompatActivity() {
    private var viewModel: ArticleViewModel = ArticleViewModel()
    private lateinit var binding: ArticleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseIntent()
        enableEdgeToEdge()
        binding = ArticleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.article_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        loadUrl()
    }

    private fun parseIntent() {
        intent.apply {
            viewModel.urlString = getStringExtra("urlString").orEmpty()
        }
    }

    private fun setupView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true

            webViewClient = object: WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.visibility = View.GONE
                }
            }

            webChromeClient = object: WebChromeClient() {

            }
        }
    }

    private fun loadUrl() {
        binding.webView.loadUrl(viewModel.urlString)
    }
}