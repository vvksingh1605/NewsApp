package net.test.newsappvivek.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.paging.LoadState
import kotlinx.android.synthetic.main.news_headline_frag.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.test.newsappvivek.NewsViewModel
import net.test.newsappvivek.R
import net.test.newsappvivek.adapter.NetworkStateAdapter
import net.test.newsappvivek.adapter.NewsListAdapter


@Suppress("UNCHECKED_CAST")
class NewsHeadlineFragment :Fragment(){
    private lateinit var viewModel: NewsViewModel
    lateinit var adapter:NewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_headline_frag,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= (activity as NewsHomeActivity).viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsAdapter()
    }

    private fun initNewsAdapter() {
        swipe_data.setOnRefreshListener { adapter.refresh() }

        context?.let {
            adapter = NewsListAdapter(it){
               val frag = NewsDetailFragment.newInstance(it)

                viewModel.detailEvent.value=it
            }
            // loader view handling
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = NetworkStateAdapter(adapter),
                footer = NetworkStateAdapter(adapter)
            )

        }
        // swipe to refresh data
        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest { loadStates ->
                swipe_data.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
        // flow observer
        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            viewModel.news.collectLatest {
                adapter.submitData(it)
            }
        }
    }

}