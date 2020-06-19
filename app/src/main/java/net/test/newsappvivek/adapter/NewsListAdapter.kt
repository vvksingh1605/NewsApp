package net.test.newsappvivek.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.test.newsappvivek.R
import net.test.newsappvivek.data.Article


class NewsListAdapter(private val context: Context,private val viewDetail: (Article) -> Unit
) :
    PagingDataAdapter<Article, NewsListAdapter.ViewHolder>(NewsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_list_item, null)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article?) {
            mTvHeadline.text=article?.title
            mTvNewsPublishTime.text=article?.publishedAt
            mTvSource.text=article?.author
            itemView.setOnClickListener {

                article?.let{
                    viewDetail(article)
                }

            }

            Picasso.get()
                .load(article?.urlToImage)
                .fit().centerCrop(Gravity.TOP)
                .into(mImgHeadLine)


        }
        var mTvHeadline: TextView = itemView.findViewById(R.id.news_headline)
        var mTvNewsPublishTime: TextView = itemView.findViewById(R.id.news_time)
        var mTvSource: TextView = itemView.findViewById(R.id.news_source)
        var mImgHeadLine: ImageView = itemView.findViewById(R.id.imgHead)


    }

}