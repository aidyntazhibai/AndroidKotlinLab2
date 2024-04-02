package kz.android.dogs


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kz.android.dogs.databinding.FragmentDogListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null
    private val binding get() = _binding!!
    private val adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.etSearch.setOnEditorActionListener { it, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                RetrofitObject.api.getDogByName(it.text.toString()).enqueue(object : Callback<List<Dog>> {
                    override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                        if (response.isSuccessful) {
                            adapter.submitList(response.body())
                        } else {
                            Log.e("Network", response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                        Log.e("Network", t?.message.toString())
                    }
                })
                true
            } else false
        }
    }

}

