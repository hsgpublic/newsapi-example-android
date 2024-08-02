package hsgpublic.example.newsapi.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.databinding.HeadlineItemBinding
import kotlinx.coroutines.Dispatchers

class TopHeadlinesAdapter(
    private var headlines: List<HeadlineModel>,
    private val onItemClick: (position: Int, headline: HeadlineModel) -> Unit
): RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HeadlineItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val headline = headlines[position]
        val imageUrlString = headline.urlToImage.orEmpty()
        val imageUri = Uri.parse(imageUrlString)
        val placeholderId = android.R.drawable.ic_menu_gallery
        holder.imageView.load(imageUri) {
            dispatcher(Dispatchers.IO)
            memoryCacheKey(imageUrlString)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCacheKey(imageUrlString)
            diskCachePolicy(CachePolicy.ENABLED)
            transformations(RoundedCornersTransformation(15F))
            placeholder(placeholderId)
            error(placeholderId)
            fallback(placeholderId)
        }
        holder.titleTextView.text = headline.title.orEmpty()
        holder.publishInfoTextView.text = "${headline.publishedAt.orEmpty()} by ${headline.author.orEmpty()}"
        holder.binding.root.setOnClickListener {
            onItemClick(position, headline)
        }
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

    class ViewHolder(
        val binding: HeadlineItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageView
        val titleTextView = binding.titleTextView
        val publishInfoTextView = binding.publishInfoTextView
    }

    fun setupData(headlines: List<HeadlineModel>) {
        this.headlines = headlines.orEmpty()
        notifyDataSetChanged()
    }
}