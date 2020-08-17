package net.test.newsappvivek.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
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
        //broadCastnewsListScreen()
        viewModel.detailEvent.observe(this, Observer {
            broadCastDetailScreen(NewsDetailFragment.newInstance(it))
        })
    }
//    /**
//     * open news list fragment
//     */
//    private fun broadCastnewsListScreen() {
//        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, NewsHeadlineFragment())
//            .addToBackStack(NewsHeadlineFragment::class.java.canonicalName)
//            .commitAllowingStateLoss()
//    }

    /**
     * open detail screen of news item
     */
    private fun broadCastDetailScreen(fragment: NewsDetailFragment) {
        val navigationController=fragment.findNavController()
        navigationController.navigate(R.id.action_newsHeadlineFragment_to_newsDetailFragment)


//        val frag =
//            supportFragmentManager.fragments.get(supportFragmentManager.backStackEntryCount - 1)
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.hide(frag)
//        transaction.add(R.id.frame_layout, fragment)
//            .addToBackStack(NewsDetailFragment::class.java.canonicalName).commitAllowingStateLoss()
    }

//    override fun onBackPressed() {
//        // fragment back handling , it can be more generic
////        supportFragmentManager.apply {
////            if (backStackEntryCount > 1) {
////                popBackStack()
////                val frag =
////                    fragments.get(supportFragmentManager.backStackEntryCount - 1)
////                val transaction = supportFragmentManager.beginTransaction()
////                transaction.show(frag)
////            } else {
////                finish()
////            }
////        }
//
//    }
}
