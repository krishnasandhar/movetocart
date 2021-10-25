package dev.company.jetshop.cart

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R

class CartActivity: AppCompatActivity(), CartListAdapter.deleting{

    var cartlist:ListView?=null
    var sqliteDB:SQLiteDatabase?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartlist = findViewById (R.id.cartlist)
        sqliteDB = this.openOrCreateDatabase("cartregister.db", MODE_APPEND,null)
    }
    @SuppressLint("Recycle")
    override fun onResume() {
        super.onResume()

        val readdata = sqliteDB?.rawQuery("select * from user_cartregister", null)

        val productsdetail = ArrayList<ProductDetails>()

        while (readdata!!.moveToNext()) {

            val product = ProductDetails ()
            product.id = readdata.getInt(0)
            product.Productname=  readdata.getString(1)
            product.Productprice = readdata.getString(2)
            product.Date = readdata.getString(3)
            productsdetail.add(product)
        }
        val adapter = CartListAdapter(this@CartActivity,productsdetail,layoutInflater,this)
        cartlist?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun delete(value: String?) {
        onResume()
    }
}