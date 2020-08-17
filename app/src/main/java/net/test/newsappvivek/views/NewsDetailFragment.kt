package net.test.newsappvivek.views

import android.os.Bundle
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_detail_frag.*
import net.test.newsappvivek.AppConstant.BUNDLE_ARTICLE

import net.test.newsappvivek.R
import net.test.newsappvivek.data.Article

class NewsDetailFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_detail_frag,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     arguments?.let {
         val selectedArticle=NewsDetailFragmentArgs.fromBundle(it).selectedArticle
         setDetails(selectedArticle)
     }

    }

    private fun setDetails(article: Article) {
        tv_desc.text=article.description
        tv_source.text=article.author
        tv_time.text=article.publishedAt
        tv_heading.text=article.title
        Picasso.get()
            .load(article.urlToImage)
            .fit().centerCrop(Gravity.TOP)
            .into(imgDesc)


    }
companion object {

    fun newInstance(article: Article?):NewsDetailFragment {
        val bundle = Bundle().also {
            it.putSerializable(BUNDLE_ARTICLE, article)
        }
        val frag = NewsDetailFragment().also {
            it.arguments = bundle
        }

        return frag
    }
}
}