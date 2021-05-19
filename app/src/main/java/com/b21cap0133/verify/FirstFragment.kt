package com.b21cap0133.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.b21cap0133.verify.databinding.FragmentFirstBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        const val IDENTIFIER = "IDENTIFIER"
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carousel: ImageCarousel = binding.carouselView
        val list = mutableListOf<CarouselItem>()
        list.add(CarouselItem(imageDrawable = R.drawable.banner_1, "Info hoax terbaru"))
        list.add(CarouselItem(imageDrawable = R.drawable.banner_2, "Apa itu hoax?"))
        carousel.addData(list)
        carousel.onItemClickListener = object : OnItemClickListener {
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                when (position) {
                    1 -> view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }

            override fun onLongClick(position: Int, dataObject: CarouselItem) {
                //NOTHING
            }
        }
        addClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addClickListeners(){
        val bundle = Bundle()
        binding.iconTurnbackhoax.setOnClickListener {
            bundle.putInt(IDENTIFIER, 1)
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_appSwitchFragment, bundle)
        }
        binding.iconCheckhoax.setOnClickListener {
            bundle.putInt(IDENTIFIER, 2)
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_appSwitchFragment, bundle)
        }
        binding.iconJalahoax.setOnClickListener {
            bundle.putInt(IDENTIFIER, 3)
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_appSwitchFragment, bundle)
        }
        binding.iconJakartaPost.setOnClickListener {
            bundle.putInt(IDENTIFIER, 4)
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_appSwitchFragment, bundle)
        }
        binding.iconJaki.setOnClickListener {
            bundle.putInt(IDENTIFIER, 5)
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_appSwitchFragment, bundle)
        }
        binding.iconAbout.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_aboutFragment)
        }
    }
}
