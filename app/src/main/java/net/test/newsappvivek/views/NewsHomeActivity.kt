package net.test.newsappvivek.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.test.newsappvivek.NewsViewModel
import net.test.newsappvivek.R

class NewsHomeActivity : AppCompatActivity() {

    val viewModel: NewsViewModel by viewModels {
        object : AbstractSavedStateViewModelFactory(this, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return NewsViewModel(application!!) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        broadCastnewsListScreen()
        viewModel.detailEvent.observe(this, Observer {
            broadCastDetailScreen(NewsDetailFragment.newInstance(it))
        })
    }

    private fun broadCastnewsListScreen() {
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, NewsHeadlineFragment())
            .addToBackStack(NewsHeadlineFragment::class.java.canonicalName)
            .commitAllowingStateLoss()
    }

    private fun broadCastDetailScreen(fragment: NewsDetailFragment) {
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragment)
            .addToBackStack(NewsDetailFragment::class.java.canonicalName).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 1) {
                popBackStack()
            } else {
                finish()
            }
        }

    }
}
