package com.dionis.datastore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dionis.datastore.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    private lateinit var userManager: UserManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        userManager = UserManager(requireContext())
        readDataUser()
        setUp()
    }

    private fun setUp() {

        setupClicks()
    }

    private fun setupClicks() {

        navigation()

    }


    private fun navigation() {
        binding.btnSave.setOnClickListener {
            saveDataUserValidate()
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)

        }
    }

    private fun saveDataUserValidate() {
        if (binding.cbSave.isChecked) {
            saveDataUser()
        }

    }

    private fun saveDataUser() {

        val name = binding.tvDigite.text.toString()
        val secondName = binding.tvDigite2.text.toString()
        val authenticated = binding.cbSave.isChecked

        lifecycleScope.launch { userManager.saveDataUser(name, secondName, authenticated) }
    }

    private fun readDataUser() {
        lifecycleScope.launch {
            val user = userManager.readDataUser()

            binding.tvDigite.setText(user.name)
            binding.tvDigite2.setText(user.secondName)
            binding.cbSave.isChecked = user.authenticated
        }
    }


}
