package com.dionis.datastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dionis.datastore.databinding.FragmentSecondBinding
import kotlinx.coroutines.launch


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userManager = UserManager(requireContext())
        setUp()

    }

    private fun setUp() {
        setUpClicks()
    }

    private fun setUpClicks() {
        logOut()

    }

    private fun logOut() {
        binding.btSair.setOnClickListener {
            clearDataUser()
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    private fun clearDataUser() {
        if (binding.cbUnSave.isChecked) {
            lifecycleScope.launch { userManager.clearDataUser() }
        }
    }

}