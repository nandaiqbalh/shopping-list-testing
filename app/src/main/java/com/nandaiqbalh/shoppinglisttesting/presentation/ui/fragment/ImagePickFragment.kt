package com.nandaiqbalh.shoppinglisttesting.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nandaiqbalh.shoppinglisttesting.R
import com.nandaiqbalh.shoppinglisttesting.presentation.ui.ShoppingViewModel

class ImagePickFragment : Fragment(R.layout.fragment_image_pick){

	lateinit var viewModel: ShoppingViewModel

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
	}
}