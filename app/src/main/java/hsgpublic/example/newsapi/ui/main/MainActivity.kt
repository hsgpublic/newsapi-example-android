package hsgpublic.example.newsapi.ui.main

import android.content.Intent
import android.content.res.Configuration
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
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var topHeadlinesAdapter: TopHeadlinesAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = MainViewModel(application)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        bindView()
        bindViewModel()
        refresh()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutManager.spanCount = resources.getInteger(R.integer.grid_span_count)
    }

    private fun setupView() {
        gridLayoutManager = GridLayoutManager(
            this@MainActivity,
            resources.getInteger(R.integer.grid_span_count)
        )
        topHeadlinesAdapter = TopHeadlinesAdapter(
            listOf()
        ) { _, headline ->
            moveToArticle(headline.title, headline.articleURL)
        }

        binding.recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = topHeadlinesAdapter
        }
    }

    private fun bindView() {
        binding.refreshLayout.setOnRefreshListener {
            refresh()
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

    private fun refresh() {
        binding.refreshLayout.isRefreshing = false
        viewModel.fetchTopHeadlines("kr")
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