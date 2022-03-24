package com.example.poweractivity.fragments

import Api.UpcNoClient
import Api.UpcNoService
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poweractivity.Repository.UpcRepository
import com.example.poweractivity.Utils.App
import com.example.poweractivity.adapter.PriceItemAdapter
import com.example.poweractivity.data.RapidModel
import com.example.poweractivity.data.SearchResult
import com.example.poweractivity.databinding.FragmentBuyBinding
import com.example.poweractivity.viewModel.SharedViewModel
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BuyFragment : Fragment() {

    var itemlist: ArrayList<RapidModel> = arrayListOf()
    lateinit var binding: FragmentBuyBinding

    //lateinit var repo: UpcRepository
    var product: SearchResult? = null
    lateinit var contex: Context
    lateinit var upcNoService: UpcNoService
    lateinit var viewModel: SharedViewModel
    lateinit var repo: UpcRepository
    lateinit var keyword: String
    lateinit var title: String
    lateinit var progressDailog: ProgressDialog
    var isException = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = (it.getParcelable<SearchResult>("product") as? SearchResult)
            keyword = it.getString("keyword").toString()
            // title = it.getString("title").toString()
            Log.e("TAG", "onCreate: keyword is $keyword")

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentBuyBinding.inflate(inflater, container, false)

        binding.scrollLayout.isNestedScrollingEnabled = false




        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val title = product?.product?.title
        //binding.tvItemName.text = product?.product?.title
        //Picasso.get().load(product?.product?.main_image).into(binding.ivItemImage)


        progressDailog = ProgressDialog(contex)
        progressDailog.setTitle("Please wait")
        progressDailog.setMessage(" offers are loading!!!")
        progressDailog.setCancelable(true)
        progressDailog.show()



        Log.e("TAG", "onViewCreated: title name in buyfragment ${product?.product?.title}")

        repo = (activity?.application as App).upcRepository

        val newItemAdapter = PriceItemAdapter(requireContext(), itemlist)

        with(binding.NewitemRecyclerView) {
            //binding.itemRecyclerView1.isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newItemAdapter
            hasFixedSize()


        }


        if (keyword.isEmpty()) {
            binding.tvItemName.text = product?.product?.title
            Picasso.get().load(product?.product?.main_image).into(binding.ivItemImage)

            val retrofit = UpcNoClient.retrofitInstance
            upcNoService = retrofit.create(UpcNoService::class.java)
            /* with(binding.NewitemRecyclerView) {
                 //binding.itemRecyclerView1.isNestedScrollingEnabled = true
                 layoutManager = LinearLayoutManager(requireContext())
                 adapter = newItemAdapter
                 hasFixedSize()


             }*/
            /*viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            viewModel.getRapid().observe(requireActivity())*/
           /* repo.asinNo(App.RAPID_API_HOST,
                App.RAPID_API_KEY,
                product?.product?.title.toString(),
                "US")*/
            repo.asinResponse.observe(requireActivity())
            {
                if (it != null && it.isNotEmpty()) {
                    progressDailog.dismiss()
                    Log.e("TAG", "onViewCreated: BuyFragment not nulllllll ${product?.product?.title}")
                    itemlist.clear()
                    itemlist.addAll(it)
                    newItemAdapter.notifyDataSetChanged()

                } else {
                    binding.tvNoOffers.visibility = View.GONE
                    Log.e("TAG", "onViewCreated: buy Fragment null ${product?.product?.title}")
                }
            }


        } else {

            Log.e("TAG", "onViewCreated: keyword is  NOT null in buy fragment $keyword")
            repo.asinNo(App.RAPID_API_HOST, App.RAPID_API_KEY, keywords = keyword, "US")
            repo.asinResponse.observe(requireActivity())
            {
                if (it != null) {
                    Picasso.get().load(it[0].imageUrl).into(binding.ivItemImage)
                    binding.tvItemName.text = keyword
                    progressDailog.dismiss()
                    itemlist.clear()
                    itemlist.addAll(it)
                    newItemAdapter.notifyDataSetChanged()
                }
            }
        }


    }


    companion object {
        @JvmStatic
        fun newInstance(data: SearchResult?, keyword: String) =
            BuyFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("product", data)
                    putString("keyword", keyword)
                    //putString("title", title)
                }
            }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contex = context
    }

}