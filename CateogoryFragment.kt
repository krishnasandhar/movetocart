package dev.company.jetshop.ui.cateogory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.company.jetshop.AquagymActivity
import dev.company.jetshop.R
import dev.company.jetshop.products.*

class CateogoryFragment: Fragment(), CateogoryAdapter.Clicklistener {

    lateinit var searchView:SearchView
    private var recyclerView:RecyclerView?=null
    private var gridLayoutManager:GridLayoutManager?=null
    private var itemsModallist:ArrayList<CateogoryModal>?=null
    private var itemsAdapter:CateogoryAdapter?=null

    val image = arrayOf (
        CateogoryModal(R.drawable.placeholder,"Aquagym"),
        CateogoryModal(R.drawable.placeholder,"Archery"),
        CateogoryModal(R.drawable.placeholder,"Baseball"),
        CateogoryModal(R.drawable.placeholder,"Badminton"),
        CateogoryModal(R.drawable.placeholder,"Baseketball"),
        CateogoryModal(R.drawable.placeholder,"Carrom"),
        CateogoryModal(R.drawable.placeholder,"Cricket"),
        CateogoryModal(R.drawable.placeholder,"Darts"),
        CateogoryModal(R.drawable.placeholder,"Dance"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val views =  inflater.inflate(R.layout.fragment_cateogory, container, false)

        for (items in image) {

            itemsModallist?.add(items)
        }

        recyclerView = views.findViewById(R.id.recycleView) as RecyclerView
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        itemsModallist = ArrayList()
        itemsAdapter = CateogoryAdapter(this,itemsModallist!!)
        itemsAdapter?.setData(itemsModallist!!)
        recyclerView?.adapter = itemsAdapter

        recyclerView?.setHasFixedSize(true)

        searchView = views.findViewById (R.id.searchview)

        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener,SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }
            override fun onQueryTextChange(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }
        })

        return views
    }

    override fun Clickeditem(itemsModal: CateogoryModal) {
        Log.e("TAG", itemsModal.alphaChar!!)

        when (itemsModal.alphaChar) {

            "Aquagym"->
                startActivity(Intent(getActivity(), AquagymActivity::class.java))
            "Archery"->
                startActivity(Intent(getActivity(), ArcheryActivity::class.java))

            else-> {

                Toast.makeText(getActivity(), "No Action", Toast.LENGTH_SHORT).show()

            }
        }
    }
}