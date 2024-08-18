package saif.saif.moveinsync

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saif.moveinsync.R
import kotlin.random.Random

class CardHorizontalAdapter(
    private val textList : ArrayList<String>,
    private val imageList: ArrayList<Int>,
    private val colorList: ArrayList<Int>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CardHorizontalAdapter.ImageViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card : View = itemView.findViewById(R.id.cardView)
        val textView: TextView = itemView.findViewById(R.id.itemText)
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_text_view_item, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        holder.textView.text = textList[position]
        Log.d("ColorList", "ColorList size is ${Random.nextInt(colorList.size)}")
        val index = Random.nextInt(colorList.size)
        holder.card.setBackgroundResource(colorList[index])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = imageList.size
}