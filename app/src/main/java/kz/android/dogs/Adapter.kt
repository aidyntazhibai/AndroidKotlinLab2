package kz.android.dogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.android.dogs.databinding.DogItemBinding

class Adapter : ListAdapter<Dog, Adapter.DogViewHolder>(DogBreedDiffCallback()) {


   class DogBreedDiffCallback:DiffUtil.ItemCallback<Dog>(){
       override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
           return oldItem.name == newItem.name
       }

       override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
       }

   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DogItemBinding.inflate(layoutInflater, parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
      holder.bind(getItem(position))
    }

    class DogViewHolder(private val binding: DogItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dogBreed: Dog) {
            with(binding) {
                tvDogName.text = dogBreed.name
                tvGoodWithChildren.text = "Good with Children: ${dogBreed.goodWithChildren}"
                tvGoodWithOtherDogs.text = "Good with Other Dogs: ${dogBreed.goodWithOtherDogs}"
                tvEnergy.text = "Energy: ${dogBreed.energy}"
                tvLifeExpectancy.text = "Life Expectancy: ${dogBreed.minLifeExpectancy}-${dogBreed.maxLifeExpectancy} years"

                Glide.with(img.context)
                    .load(dogBreed.imageLink)
                    .placeholder(R.drawable.placeholder)
                    .into(img)

            }
        }
    }
}
