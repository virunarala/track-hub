package dev.virunarala.trackhub.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.virunarala.trackhub.R
import dev.virunarala.trackhub.data.db.model.RepositoryEntity
import dev.virunarala.trackhub.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    private lateinit var adapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setMenuClickListener()
        collectReposFlow()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRepos()
    }

    private fun initRecyclerView() {
        val orientation = (binding.rvRepos.layoutManager as LinearLayoutManager).orientation
        val dividerItemDecoration = DividerItemDecoration(binding.rvRepos.context,orientation)
        binding.rvRepos.addItemDecoration(dividerItemDecoration)
        adapter = HomeAdapter(
            onRepoItemClick = { repo ->
                openRepo(repo)
            },
            onShareRepoClick = { repo ->
                shareRepo(repo)
            }
        )
        binding.rvRepos.adapter = adapter
    }

    private fun setMenuClickListener() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.add_repo -> {
                    findNavController().navigate(R.id.action_homeFragment_to_addRepoFragment)
                    true
                }
            }

            false
        }
    }

    private fun openRepo(repo: RepositoryEntity) {
        val uri = Uri.parse(repo.htmlUrl)
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(browserIntent)
    }

    private fun shareRepo(repo: RepositoryEntity) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        val shareMessage = getString(R.string.share_repo_message,repo.name,repo.htmlUrl)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.hint_share_repo)))
    }

    private fun collectReposFlow() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reposFlow?.collect { repositories ->
                    repositories?.let {
                        if(repositories.isEmpty()) {
                            showNoReposMessage()
                        } else {
                            showRepos()
                            adapter.submitList(it)
                        }
                    }
                }
            }
        }
    }

    private fun showNoReposMessage() {
        binding.rvRepos.visibility = View.GONE
        binding.textNoRepos.visibility = View.VISIBLE
    }

    private fun showRepos() {
        binding.textNoRepos.visibility = View.GONE
        binding.rvRepos.visibility = View.VISIBLE
    }

}