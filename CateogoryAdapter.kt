package dev.company.jetshop.ui.cateogory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import dev.company.jetshop.R

class CateogoryAdapter (var clickListener: Clicklistener, var ItemsModalList: ArrayList<CateogoryModal>)
    : RecyclerView.Adapter<CateogoryAdapter.ItemsAdapterVH>(), Filterable {

    var ItemsModalListFilter = ArrayList<CateogoryModal>()

    fun setData (itemsModalList: ArrayList<CateogoryModal>) {
        this.ItemsModalList = itemsModalList
        this.ItemsModalListFilter = itemsModalList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ItemsAdapterVH {
        return ItemsAdapterVH (LayoutInflater.from(parent.context).inflate(R.layout.caetogorylist, parent, false))

    }
    override fun onBindViewHolder (holder: ItemsAdapterVH, position: Int) {

        val itemsModal = ItemsModalList [position]
        holder.name.text  = itemsModal.alphaChar
        holder.image.setImageResource(itemsModal.iconschar!!)

        holder.itemView.setOnClickListener {
            clickListener.Clickeditem(itemsModal)
        }
    }
    override fun getItemCount(): Int {
        return ItemsModalList.size
    }
    class ItemsAdapterVH (itemView: View)  : RecyclerView.ViewHolder(itemView)  {

        var name: TextView = itemView.findViewById (R.id.alpha_text_view)
        var image: ImageView = itemView.findViewById(R.id.icons_image)

    }
    interface Clicklistener {
        fun Clickeditem (itemsModal: CateogoryModal)
    }
    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {

                val filterResults = FilterResults()
                if (charsequence == null || charsequence.length<0) {
                    filterResults.count = ItemsModalListFilter.size
                    filterResults.values = ItemsModalListFilter

                } else {

                    val searchchar = charsequence.toString().toLowerCase()

                    val itemModal = ArrayList<CateogoryModal>()

                    for (item in ItemsModalListFilter) {
                        if (item.alphaChar!!.contains(searchchar)) {
                            itemModal.add(item)

                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }

                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {

                ItemsModalList = filterResults!!.values as ArrayList<CateogoryModal>
                notifyDataSetChanged()
            }
        }
    }
}