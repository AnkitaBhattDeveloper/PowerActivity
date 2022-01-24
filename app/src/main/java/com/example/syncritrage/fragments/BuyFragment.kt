package com.example.syncritrage.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.databinding.FragmentBuyBinding
import com.example.syncritrage.adapter.PriceItemAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BuyFragment : Fragment() {


    // lateinit var recyclerView: RecyclerView
    private lateinit var itemlist: ArrayList<String>
    private lateinit var itemlist2: ArrayList<String>
    private lateinit var itemlist3: ArrayList<String>

    // lateinit var context: Context

    lateinit var binding: FragmentBuyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {

        binding = FragmentBuyBinding.inflate(inflater, container, false)

        binding.scrollLayout.isNestedScrollingEnabled = false
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.f



        itemlist = arrayListOf()
        itemlist2 = arrayListOf()
        itemlist3 = arrayListOf()

        val newItemAdapter = PriceItemAdapter(requireContext(), itemlist, "NEW")
        val usedItemAdapter = PriceItemAdapter(requireContext(), itemlist2, "USED")
        val fbaItemAdapter = PriceItemAdapter(requireContext(), itemlist3, "FBA")

        //  object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }


        with(binding.NewitemRecyclerView) {
            //binding.itemRecyclerView1.isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newItemAdapter
            hasFixedSize()

            itemlist.add("150$")
            itemlist.add("50$")
            itemlist.add("750$")
            itemlist.add("10$")
            itemlist.add("507$")
            itemlist.add("70$")
            newItemAdapter.notifyDataSetChanged()
        }
        with(binding.UseditemRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usedItemAdapter
            hasFixedSize()
//            recyclerView.setLayoutFrozen(true)
            itemlist2.add("10$")
            itemlist2.add("507$")


            usedItemAdapter.notifyDataSetChanged()
        }
        with(binding.FbaItemRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = fbaItemAdapter
            hasFixedSize()

            itemlist3.add("19$")
            itemlist3.add("57$")
            itemlist3.add("79$")
            itemlist3.add("10$")
            itemlist3.add("507$")
           fbaItemAdapter.notifyDataSetChanged()
        }

      //  ViewCompat.setNestedScrollingEnabled(binding.FbaItemRecyclerView, false)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BuyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}