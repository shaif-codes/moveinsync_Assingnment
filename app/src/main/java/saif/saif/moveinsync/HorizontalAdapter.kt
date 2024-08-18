package saif.saif.moveinsync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
//import com.javed.moveinsync.R
import com.saif.moveinsync.R

class HorizontalAdapter(
    private val imageList: ArrayList<Int>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HorizontalAdapter.ImageViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.horizontalCardViewImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_card_view_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = imageList.size
}
