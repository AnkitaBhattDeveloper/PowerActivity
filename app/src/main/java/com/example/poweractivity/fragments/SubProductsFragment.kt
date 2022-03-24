package com.example.poweractivity.fragments

import Api.BlueCartClient
import Api.BlueCartService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.R
import com.example.poweractivity.Repository.BlueCartRepository
import com.example.poweractivity.Repository.UpcRepository
import com.example.poweractivity.Utils.App
import com.example.poweractivity.adapter.SubProductAapter
import com.example.poweractivity.data.RapidModel
import com.example.poweractivity.data.SearchResult
import com.example.poweractivity.databinding.FragmentSubProductsBinding
import com.example.poweractivity.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_sub_products.*

class SubProductsFragment : Fragment() {
    lateinit var binding: FragmentSubProductsBinding
    var subProductList: ArrayList<SearchResult?> = arrayListOf()
    val oldArrayList: ArrayList<SearchResult?> = arrayListOf()
    val amazonList: ArrayList<RapidModel> = arrayListOf()
    lateinit var gridLayoutManager:GridLayoutManager


    //lateinit var adapter: viewPagerAdapter
    lateinit var subProductAapter: SubProductAapter
    lateinit var repo: BlueCartRepository
    lateinit var amazonRepo: UpcRepository

    //    lateinit var amazon_repo: UpcRepository
    lateinit var productType: String
    lateinit var storeId: String
    lateinit var model: SharedViewModel
    var walmartPrice = ""
    var amazonPrice = ""
    var i: Int = 1
    var index = 0
    var isScrolling = false
    var currentItem = 0;
    var totalItem = 0;
    var scrollOutItems = 0
    var pages = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            productType = it.getString("name").toString()
            storeId = it.getString("storeId").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSubProductsBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.progressBar.visibility = View.VISIBLE

        subProductAapter = SubProductAapter(requireContext(), subProductList)
        gridLayoutManager = GridLayoutManager(context, 2)
        val cartService = BlueCartClient.blueCartRetrofit().create(BlueCartService::class.java)
        repo = BlueCartRepository(cartService, requireContext())
        with(binding.rvProducts) {
            adapter = subProductAapter
            layoutManager = gridLayoutManager
            hasFixedSize()


        }

        val blue_cart =
            BlueCartClient.blueCartRetrofit().create(BlueCartService::class.java)
        repo = BlueCartRepository(blue_cart, requireContext())
        amazonRepo = (activity?.application as App).upcRepository


        if (storeId.isNullOrEmpty()) {

            repo.search(App.BLUE_CART_API_KEY,
                "search",
                productType,
                App.getString(requireContext(), App.USER_POSTAL_CODE).toString(), i.toString())

            Log.e("TAG", "onViewCreated product name: $productType")
            repo.searchResponse.observe(requireActivity())
            {

                if (it != null) {
                    binding.progressLayout.visibility = View.GONE

                    Log.e("TAG", "onViewCreated: ${it.search_results.size}")
                    it.let {
                        subProductList.addAll(it.search_results)
                        subProductAapter.notifyDataSetChanged()
                    }

                    pages = it.pagination.total_pages
                    Log.e("PAGESSSSS", "onViewCreated: PAGESSSSSS $pages = $i")

                }

                ScrollData()
            }
        } else {
            Log.e("TAG", "searchProduct: text ${productType}")
            repo.searchStore(App.BLUE_CART_API_KEY, "search", storeId,
                productType, i.toString())
            Log.e("TAG", "Subproduct fragment Store id is not null : value of I $i")

            repo.searchStore.observe(requireActivity())
            {
                Log.e("TAG", "onViewCreated: store is ka search walaaaaaaaaaaaaaaaaa")
                if (it != null) {
                    binding.progressLayout.visibility = View.GONE
                    Log.e("TAG",
                        "onViewCreated:  store iddd subproduct fragment productsssss${it.search_results[0]?.product}")
                    it.let {
                        oldArrayList.clear()
                        oldArrayList += it.search_results
                        // subProductAapter.notifyDataSetChanged()
                       // index = 0
                        pages = it.pagination.total_pages
                    }
                    // index = 0

                    val title = it.search_results[index]?.product?.title.toString()
                    Log.e("INDEX", "onViewCreated: index1 $index")

                    amazonPrice(title)


                }


            }
            model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            model.getRapid().observe(requireActivity())
            {

                if (it != null) {
                    amazonList.clear()
                    amazonList.addAll(it)
                    Log.e("COMPARE", "compare: compareeeeeee ${it}")
                    if (!oldArrayList.isEmpty()) {
                        compare()
                    }
                }
            }
            amazonRepo.asinResponse.observe(requireActivity()) {
                if (it != null) {
                    if (it.isEmpty()) {
                        index++
                        Log.e("INDEX", "onViewCreated: index3 $index")
                        if (oldArrayList.size > index) {
                            amazonPrice(oldArrayList[index]?.product?.title.toString())


                        } else {
                            Log.e("TAG", " size bada haiiiiiiiiiiiiiiiiiiiiiiii ")
                           // ScrollData()
                        }
                    } else {
                        model.setRapid(it)
                    }
                }



            }


        }

      /*  rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = gridLayoutManager.childCount
                totalItem = gridLayoutManager.itemCount
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (currentItem + scrollOutItems == totalItem)) {
                    if (storeId.isNullOrEmpty()) {
                        loadData()
                    } else {
                        loadStoreData()
                    }

                }


            }

        })*/


    }


    private fun replaceFragment(f: Fragment) {
        val fm = fragmentManager
        val ft = fm?.beginTransaction()
        ft?.replace(R.id.mainFrame, f)
        ft?.commit()
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(Param1: String, Param2: String) =
            SubProductsFragment().apply {
                arguments = Bundle().apply {
                    putString("name", Param1)
                    putString("storeId", Param2)
                }
            }

    }

    fun compare() {

        var isPriceLess = false

        Log.e("TAG", "compare: subproduct list ke upr wala log ${oldArrayList.size} = $index")

        if (index < oldArrayList.size) {
            walmartPrice = oldArrayList[index]?.offers?.primary?.price.toString()
            Log.e("INDEX", "onViewCreated: index2 $index")

            for (k in amazonList) {
                amazonPrice = k?.price.toString()
                if (amazonPrice == null || amazonPrice == "") {
                    amazonPrice = "0.0"
                }
                if (amazonPrice.contains("$")) {
                    amazonPrice = amazonPrice.replace("$", "")
                }
                if (amazonPrice.contains(",")) {
                    amazonPrice = amazonPrice.replace(",", "")
                }
                amazonPrice = amazonPrice.replaceAfter(" ", "")
                Log.e(
                    "ComparisonBetweenTitle",
                    "compare: Compareeeeeee list $amazonPrice = $walmartPrice",
                )


                if (amazonPrice.toDouble() < walmartPrice.toDouble()) {
                    //oldArrayList.clear()
                    Log.e(
                        "PRICETAG",
                        "compare: Price is less or same of $amazonPrice than $walmartPrice",
                    )

                    isPriceLess = true


                } else {
                    Log.e("TAG", "compare: failed to calculate the price")
                }


            }
            if (isPriceLess) {
                subProductList.add(oldArrayList[index])
                subProductAapter.notifyDataSetChanged()
            }
            index++
            Log.e("INDEX", "onViewCreated: index3 $index")
            if (oldArrayList.size > index) {
                amazonPrice(oldArrayList[index]?.product?.title.toString())


            } else {
                Log.e("TAG", " size bada haiiiiiiiiiiiiiiiiiiiiiiii ")
                index = 0
                ScrollData()
            }
        } else {
            Log.e("Index", "compare: INDEX is greater than OLd list $index")
           // index = 0
            ScrollData()
        }

    }

    private fun amazonPrice(keyword: String) {
        amazonRepo.asinNo(App.RAPID_API_HOST, App.RAPID_API_KEY, keyword, "US")


    }

    private fun loadData() {

        if (i <= pages) {

            repo.search(App.BLUE_CART_API_KEY,
                "search",
                productType,
                App.getString(requireContext(), App.USER_POSTAL_CODE).toString(), i++.toString())
        } else {
            Log.e("TAG", "loadData: no more product to load")
        }

    }

    private fun loadStoreData() {
        if (i <= pages) {
            repo.searchStore(App.BLUE_CART_API_KEY, "search", storeId,
                productType, i++.toString())
        } else {
            Log.e("TAG", "loadStoreData: no more product to load")
        }

    }

    private fun ScrollData() {
        binding.rvProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = gridLayoutManager.childCount
                totalItem = gridLayoutManager.itemCount
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition()
                if (isScrolling && (currentItem + scrollOutItems == totalItem)) {
                    if (storeId.isNullOrEmpty()) {
                        loadData()
                    } else {
                        loadStoreData()
                    }

                }


            }

        })

    }

}