/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.test.newsappvivek.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.network_state_item.view.*
import net.test.newsappvivek.R

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {
    private val progressBar = itemView.progress_bar as ProgressBar
    private val errorMsg = itemView.error_msg
    private val retry = itemView.retry_button
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        if (loadState is Loading) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE

        if (loadState is Error) {
            retry.visibility = View.VISIBLE
        } else retry.visibility = View.GONE

        if ((loadState as? Error)?.error?.message.isNullOrBlank()) {
            errorMsg.visibility = View.GONE
        } else errorMsg.visibility = View.VISIBLE

        errorMsg.text = (loadState as? Error)?.error?.message
    }
}
