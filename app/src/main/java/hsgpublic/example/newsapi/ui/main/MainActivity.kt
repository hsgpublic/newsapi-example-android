package hsgpublic.example.newsapi.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import hsgpublic.example.newsapi.R
import hsgpublic.example.newsapi.databinding.ActivityMainBinding
import hsgpublic.example.newsapi.ui.article.ArticleActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var topHeadlinesAdapter: TopHeadlinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        bindViewModel()
        viewModel.fetchTopHeadlines("kr")
    }

    private fun setupView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(
                this@MainActivity, 1
            )

            topHeadlinesAdapter = TopHeadlinesAdapter(
                listOf()
            ) { _, headline ->
                moveToArticle(headline.title, headline.url)
            }

            adapter = topHeadlinesAdapter
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.headlines
                    .distinctUntilChanged()
                    .flowOn(Dispatchers.Main)
                    .collect { headlines ->
                        topHeadlinesAdapter.setupData(headlines)
                    }
            }
        }
    }

    private fun moveToArticle(title: String, urlString: String) {
        val intent = Intent(this, ArticleActivity::class.java).apply {
            putExtra("urlString", urlString)
        }
        try {
            startActivity(intent)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}