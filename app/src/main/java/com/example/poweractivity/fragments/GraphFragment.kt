package com.example.poweractivity.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.poweractivity.R
import com.example.poweractivity.Repository.KeepaRepository
import com.example.poweractivity.Utils.App
import com.example.poweractivity.data.SearchResult
import com.example.poweractivity.databinding.FragmentGraphBinding
import com.example.poweractivity.viewModel.SharedViewModel
import com.squareup.picasso.Picasso
import retrofit2.Retrofit

// TODO: Rename parameter arguments, choose names that match

class GraphFragment : Fragment() {
    lateinit var binding: FragmentGraphBinding
    lateinit var b30Days: AppCompatButton
    lateinit var b180Days: AppCompatButton
    lateinit var b365Days: AppCompatButton
    lateinit var retrofit: Retrofit
    lateinit var repo: KeepaRepository
    lateinit var cont: Context
    lateinit var product: SearchResult
    val domain: String = "1"
    lateinit var  viewModel:SharedViewModel
    lateinit var asin:String

    //lateinit var keepaApiService: KeepaApiService
    /* val url =
         "https://api.keepa.com/graphimage?key=" + App.KEEPA_API_KEY + "&domain=" + domain + "&asin=" + App.ASIN + "&new=1&used=0&range=30"
 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = (it.getParcelable<SearchResult>("product") as SearchResult)!!
            Log.e("TAG", "onCreate: App.asin = ${App.ASIN}")

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.cont = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvProdcutGraph.text = product.product?.title
        // Picasso.get().load(url).into(binding.ivGraph)

      /*  viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getItem().observe(requireActivity())
        {
            if (it!=null)
            asin = it.product.upc
        }*/


        b30Days = binding.btn30Day
        b180Days = binding.btn180Day
        b365Days = binding.btn365Day

        b30Days.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
        b180Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
            R.color.d_grey))
        b365Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
            R.color.d_grey))
        Days(30)


        b30Days.setOnClickListener {
            b30Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b180Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))
            b365Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))

            Days(30)

        }


        b180Days.setOnClickListener {
            b180Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b30Days.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.d_grey))
            b365Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))

            Days(180)
        }

        b365Days.setOnClickListener {
            b365Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b30Days.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.d_grey))
            b180Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))

            Days(365)
        }


    }


    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(data: SearchResult) =
            GraphFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("product", data)

                }
            }
    }

    private fun Days(days: Int) {
        val url =
            "https://api.keepa.com/graphimage?key=" + App.KEEPA_API_KEY + "&domain=" + domain + "&asin=" + App.ASIN+ "&amazon=1&new=1&used=1&salesrank=1&range=" + days + ""
        Picasso.get().load(url).into(binding.ivGraph)
        Log.e("TAG", "onViewCreated: urlllllllllllllllllllllllllllllllllllll $url")
    }


}