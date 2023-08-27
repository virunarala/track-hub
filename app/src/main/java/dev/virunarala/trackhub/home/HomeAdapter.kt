package dev.virunarala.trackhub.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.virunarala.trackhub.data.db.model.RepositoryEntity
import dev.virunarala.trackhub.databinding.ItemRepoBinding

class HomeAdapter(
    private val onRepoItemClick: (repo: RepositoryEntity) -> Unit,
    private val onShareRepoClick: (repo: RepositoryEntity) -> Unit,
): ListAdapter<RepositoryEntity, HomeAdapter.RepositoryViewHolder>(DiffCallback) {

    object DiffCallback: DiffUtil.ItemCallback<RepositoryEntity>() {
        override fun areItemsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bind(repository,onRepoItemClick,onShareRepoClick)
    }

    inner class RepositoryViewHolder(val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepositoryEntity, onRepoItemClick: (repo: RepositoryEntity) -> Unit, onShareRepoClick: (repo: RepositoryEntity) -> Unit,) {
            binding.textName.text = repo.name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.textName.tooltipText = repo.name
            }
            binding.textDescription.text = repo.description

            binding.layoutRepo.setOnClickListener {
                onRepoItemClick(repo)
            }

            binding.imageShare.setOnClickListener {
                onShareRepoClick(repo)
            }
        }
    }
}