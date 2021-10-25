package dev.company.jetshop.cart

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R

class CartListAdapter (
    val context: Activity,
    val arrayList: ArrayList<ProductDetails>,
    val layoutInflater: LayoutInflater,
    var d: deleting
) : BaseAdapter() {

    var sqliteDB: SQLiteDatabase?=null

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    interface deleting {
        fun delete(value: String?)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        convertView = LayoutInflater.from(context).inflate(R.layout.cartlistview, parent, false)

        val productname: TextView = convertView.findViewById(R.id.productname)
        val price: TextView = convertView.findViewById(R.id.price)
        val date: TextView = convertView.findViewById(R.id.date)
        val img_delete: ImageView = convertView.findViewById(R.id.img_delete)

        val product = ProductDetails()
        productname.text = product.Productname
        price.text = "Rs. "+product.Productprice
        date.text = "Date: "+product.Date

        img_delete.setOnClickListener {

            deleteData(product.id)

        }
        return convertView
    }
    @SuppressLint("WrongConstant")
    private fun deleteData(id: Int) {

        sqliteDB = context.openOrCreateDatabase("cartregister.db", Context.MODE_APPEND, null)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Product")
        builder.setMessage("Do you want delete this Product?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            sqliteDB?.execSQL("delete from user_cartregister where  id="+id+" ")
            notifyDataSetChanged()
            dialog.dismiss()
            d.delete("delete")
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}