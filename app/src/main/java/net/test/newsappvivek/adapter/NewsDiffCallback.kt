package net.test.newsappvivek.adapter;

import androidx.recyclerview.widget.DiffUtil
import net.test.newsappvivek.data.Article

class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }


}
  