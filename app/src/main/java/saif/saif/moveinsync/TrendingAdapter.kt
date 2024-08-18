package saif.saif.moveinsync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
//import com.javed.moveinsync.R
import com.saif.moveinsync.R

class TrendingAdapter(private val imageList: ArrayList<Int>, private val viewPager2: ViewPager2, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TrendingAdapter.ImageViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trending_card_view, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        if (position == imageList.size-1){
            viewPager2.post(runnable)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}