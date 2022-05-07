package com.example.student_community.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.student_community.R
import com.example.student_community.models.webapi.Address
import com.example.student_community.models.webapi.Post
import com.example.student_community.ui.holders.LoadingHolder
import com.example.student_community.ui.holders.PostHolder

class PostListAdapter(var posts: ArrayList<Post>, private val itemClick: (Post) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_Loading = 0
    private val VIEW_TYPE_Normal = 1

    override fun getItemViewType(position: Int): Int {
        if (posts[position].Id == 0)
            return VIEW_TYPE_Loading

        return VIEW_TYPE_Normal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_Loading)
            return LoadingHolder(inflater.inflate(R.layout.loading_item, parent, false))
        return  PostHolder(inflater.inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post= posts[position]
        if (holder is PostHolder) {
            holder.txtTitle.text=post.Title
            holder.txtContent.text=post.Content
            holder.txtAddress.text=post.Address.Address
        }
    }

    override fun getItemCount(): Int {
       return  posts.size
    }

    fun addLoading(){
        var loadingPost=Post.createEmptyPost()
        posts.add(loadingPost)
        notifyItemInserted(posts.size-1)
    }
    fun  removeLoading(){
        posts.removeAt(posts.size-1)
        notifyItemRemoved(posts.size)
    }

    fun addPosts(products:ArrayList<Post>) {
        this.posts.addAll(products)
        notifyDataSetChanged()
    }
}