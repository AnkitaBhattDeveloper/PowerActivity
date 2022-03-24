package com.example.poweractivity.fragments

import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class VariantFragment : Fragment()
/*{


    lateinit var binding:FragmentVariantBinding
    lateinit var cont: Context
    lateinit var variant:Variant

    *//*var ilist:ArrayList<SearchResult> = arrayListOf()
    lateinit var iLayoutManager: LinearLayoutManager*//*


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
             variant = (it.getParcelable<Variant>("variant"))as Variant

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
        binding = FragmentVariantBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvVariantText.text = variant.title
        Picasso.get().load(variant.image).into(binding.ivVariantImage)
    }


    companion object {

        fun newInstance(dataVariant:Variant) =
            VariantFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("variant", dataVariant)

                }
            }

    }
}*/
