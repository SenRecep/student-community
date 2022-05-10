package com.example.student_community.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.student_community.R
import com.example.student_community.models.webapi.Address
import com.example.student_community.models.webapi.Post
import com.example.student_community.models.webapi.dto.PostListDto
import com.example.student_community.ui.holders.LoadingHolder
import com.example.student_community.ui.holders.PostHolder

class PostListAdapter(
    var posts: ArrayList<PostListDto>,
    private val itemClick: (PostListDto) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1


    override fun getItemViewType(position: Int): Int {

        return if (posts[position].Id == 0) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_NORMAL
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING)
            return LoadingHolder(
                inflater.inflate(
                    R.layout.loading_item,
                    parent,
                    false
                )
            )
        return PostHolder(
            inflater.inflate(
                R.layout.post_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = posts[position]
        if (holder is PostHolder) {
            holder.txtTitle.text = post.Title
            holder.txtContent.text = post.Content
            holder.txtAddress.text = post.Address.Address
            holder.textDistance.text = post.Address.DistanceText
            holder.textDate.text=post.UpdatedTimeText
            holder.itemView.setOnClickListener {
                itemClick(post)
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    fun addLoading() {
        var loadingPost = Post.createEmptyPost().parseToListDto()
        posts.add(loadingPost)
        notifyDataSetChanged()


    }

    fun removeLoading() {
        var position = posts.size - 1
        posts.removeAt(position)
        notifyDataSetChanged()
    }


    fun addPosts(newProducts: ArrayList<PostListDto>) {
        posts.addAll(newProducts)
        notifyDataSetChanged()
    }
}