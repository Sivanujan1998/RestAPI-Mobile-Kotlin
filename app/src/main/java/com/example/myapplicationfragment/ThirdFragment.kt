package com.example.myapplicationfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplicationfragment.Posts.api.PostApi
import com.example.myapplicationfragment.Posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ThirdFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("im inThird fragment","oncreate view created")
        var retrofit = Retrofit.Builder()
                .baseUrl(PostApi.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var postApi = retrofit.create(PostApi::class.java)
        var postCall=postApi.listdetails()

        postCall?.enqueue(object:Callback<List<Post?>?>{
            override fun onFailure(call:  Call<List<Post?>?>, t: Throwable) {
                view.findViewById<TextView>(R.id.texttitle).text= "Connection error"
                Toast.makeText(getActivity(), "API connection Failed", Toast.LENGTH_LONG).show();


            }

            override fun onResponse(call:  Call<List<Post?>?>, response: Response<List<Post?>?>) {
                Toast.makeText(getActivity(), "API Sucessfully connected", Toast.LENGTH_LONG).show();
                var postlist:  List<Post>?=response.body() as List<Post>
                var post = arrayOfNulls<String>(postlist!!.size)


                for(i in postlist!!.indices) {
                    post[i] = "Id :- "+postlist!![i]!!.id.toString()+ "\n"
                    post[i] +=  "Title :- "+postlist!![i]!!.title+ "\n"
                }


              var adapter= ArrayAdapter<String>(activity!!.applicationContext,android.R.layout.simple_dropdown_item_1line,post)
                view.findViewById<ListView>(R.id.listview).adapter=adapter




            }

        })



        view.findViewById<Button>(R.id.button_thirdtofirst).setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }

    }
}

