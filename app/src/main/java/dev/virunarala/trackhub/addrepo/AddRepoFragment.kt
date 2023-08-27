package dev.virunarala.trackhub.addrepo

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.virunarala.trackhub.R
import dev.virunarala.trackhub.data.network.model.NetworkResult
import dev.virunarala.trackhub.data.network.model.Repository
import dev.virunarala.trackhub.databinding.FragmentAddRepoBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddRepoFragment : Fragment() {

    companion object {
        private const val TAG = "AddRepoFragment"
    }

    private var _binding: FragmentAddRepoBinding? = null
    private val binding: FragmentAddRepoBinding
        get() = _binding!!

    private val viewModel: AddRepoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRepoBinding.inflate(inflater,container,false)

        savedInstanceState?.let {
            restoreUserInput(savedInstanceState)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveUserInput(outState)
    }

    private fun init() {
        setAddRepoClickListeners()
        setNavigationIconClickListener()
        collectAddedRepoFlow()
    }

    private fun setAddRepoClickListeners() {
        binding.textInputRepoName.editText?.setOnEditorActionListener { view, actionId, event ->
            if(actionId==EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard()
                validateAndGetRepo()
                true
            } else {
                false
            }
        }

        binding.buttonAddRepo.setOnClickListener {
            hideSoftKeyboard()
            validateAndGetRepo()
        }
    }

    private fun setNavigationIconClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectAddedRepoFlow() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addedRepo.collect { addRepoNetworkResult ->
                    when(addRepoNetworkResult) {
                        is NetworkResult.Error -> {
                            hideLoading()
                            showErrorMessage(addRepoNetworkResult.message)
                        }

                        is NetworkResult.Loading -> {
                            showLoading()
                        }

                        is NetworkResult.Success<*> -> {
                            hideLoading()
                            val repo = addRepoNetworkResult.data as Repository
                            Snackbar.make(binding.toolbar,
                                getString(R.string.repo_added_message,repo.name),
                                Snackbar.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }

                        else -> {
                            //no-op
                        }
                    }
                }
            }
        }
    }

    private fun validateAndGetRepo() {
        val owner = binding.textInputOwner.editText?.text.toString().trim()
        val repoName = binding.textInputRepoName.editText?.text.toString().trim()
        if(owner.isBlank() || repoName.isBlank()) {
            if(owner.isBlank()) {
                binding.textInputOwner.error = getString(R.string.required)
            } else {
                binding.textInputOwner.error = null
            }

            if(repoName.isBlank()) {
                binding.textInputRepoName.error = getString(R.string.required)
            } else {
                binding.textInputRepoName.error = null
            }
        } else {
            viewModel.getRepo(owner, repoName)
        }
    }

    private fun hideSoftKeyboard() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = requireActivity().currentFocus

        if(view==null) {
            view = View(requireActivity())
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showLoading() {
        binding.buttonAddRepo.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.buttonAddRepo.isEnabled = true
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(message: String?) {
        Snackbar.make(binding.toolbar,
            message ?: getString(R.string.error_message),
            Snackbar.LENGTH_SHORT).show()
    }

    private fun saveUserInput(outState: Bundle) {
        val owner = binding.textInputOwner.editText?.text.toString()
        val repoName = binding.textInputRepoName.editText?.text.toString()
        outState.putString(Constants.BUNDLE_KEY_OWNER,owner)
        outState.putString(Constants.BUNDLE_KEY_REPO_NAME,repoName)
    }

    private fun restoreUserInput(savedInstanceState: Bundle) {
        val owner = savedInstanceState.getString(Constants.BUNDLE_KEY_OWNER)
        val repoName = savedInstanceState.getString(Constants.BUNDLE_KEY_REPO_NAME)
        binding.textInputOwner.editText?.setText(owner)
        binding.textInputRepoName.editText?.setText(repoName)
    }

}