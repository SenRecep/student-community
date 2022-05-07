package com.example.student_community.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*

class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txtTitle=view.txt_title;
    var txtContent=view.txt_content;
    var txtAddress=view.txt_address;
}