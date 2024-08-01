package hsgpublic.example.newsapi.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.databinding.HeadlineItemBinding

class TopHeadlinesAdapter(
    private val headlines: List<HeadlineModel>,
    private val onItemClick: (position: Int) -> Unit
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
        val imageUri = Uri.parse(headline.urlToImage)
        holder.imageView.setImageURI(imageUri)
        holder.titleTextView.text = headline.title
        holder.publishInfoTextView.text = "${headline.publishedAt} by ${headline.author}"
        holder.binding.root.setOnClickListener {
            onItemClick(position)
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
}