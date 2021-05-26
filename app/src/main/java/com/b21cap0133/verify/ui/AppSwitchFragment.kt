package com.b21cap0133.verify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.b21cap0133.verify.R
import com.b21cap0133.verify.databinding.FragmentAppSwitchBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AppSwitchFragment : Fragment() {
    private var _binding: FragmentAppSwitchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLayout()
    }

    private fun showLayout() {
        var image: Int = R.drawable.logo_verify_big
        var text = "Pergi ke halaman verify"
        when (arguments?.getInt(MainScreenFragment.IDENTIFIER)){
            1 -> {
                image = R.drawable.turnbackhoax
                text = "Pergi ke web turnbackhoax"
            }
            2 -> {
                image = R.drawable.logo_fact_check
                text = "Pergi ke web cekfakta"
            }
            3 -> {
                image = R.drawable.logo_jalahoax
                text = "Pergi ke web JalaHoax"
            }
            4 -> {
                image = R.drawable.logo_jakarta_post
                text = "Pergi ke web The Jakarta Post"
            }
            5 -> {
                image = R.drawable.logo_jaki
                text = "Pindah ke JaKi"
            }
        }
        Glide.with(this)
            .load(image)
            .apply(RequestOptions().override(250, 250))
            .into(binding.appIcon)
        binding.button1.text = text
    }
}