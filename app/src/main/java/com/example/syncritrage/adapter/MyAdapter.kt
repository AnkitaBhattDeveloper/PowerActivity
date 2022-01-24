package com.example.syncritrage.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OVER_SCROLL_ALWAYS
import android.view.View.OVER_SCROLL_NEVER
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.poweractivity.R
import com.example.syncritrage.data.SpinnerModel


class MyAdapter(val context: Context, var dataSource: List<SpinnerModel>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        super.onLayout(changed, l, t, r, b)
//        val canScrollVertical = computeVerticalScrollRange() > height
//        overScrollMode = if (canScrollVertical) OVER_SCROLL_ALWAYS else OVER_SCROLL_NEVER
//    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[position].name

        // val id = context.resources.getIdentifier(dataSource.get(position).url, "drawable", context.packageName)
        vh.img.setImageDrawable(ContextCompat.getDrawable(context,
            dataSource[position].resourceId));

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.spinnerItemText) as TextView
        val img: ImageView = row?.findViewById(R.id.spinnerItemImage) as ImageView

    }

}



