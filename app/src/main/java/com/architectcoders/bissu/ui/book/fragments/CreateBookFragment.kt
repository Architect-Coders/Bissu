package com.architectcoders.bissu.ui.book.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.architectcoders.bissu.R
import com.architectcoders.bissu.data.server.category.CategoryDatasource as CategoryRemotelDatasource
import com.architectcoders.bissu.data.database.category.CategoryDatasource as CategoryLocalDatasource
import com.architectcoders.bissu.ui.book.CreateBookViewModel
import com.architectcoders.bissu.ui.common.app
import com.architectcoders.bissu.ui.common.getViewModel
import com.architectcoders.data.repository.CategoryRepository
import com.architectcoders.domain.Category
import com.architectcoders.usecases.GetCategories
import kotlinx.android.synthetic.main.fragmet_create_book.*

/**
 * Created by Anibal Cortez on 2020-02-16.
 */
class CreateBookFragment : Fragment() , AdapterView.OnItemSelectedListener{

    private lateinit var viewModel: CreateBookViewModel

    private lateinit var categoryList : List<Category>
    private var categorySelected : Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel {
            CreateBookViewModel(
                GetCategories(
                    CategoryRepository(
                        CategoryLocalDatasource(activity!!.app.db),
                        CategoryRemotelDatasource()
                    )
                )
            )
        }
    }

    override fun onCreateView(  inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragmet_create_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(this, Observer(::updateUi))

        viewModel.getCategories()
    }

    private fun updateUi(model: CreateBookViewModel.UiModel) {
        progress_bar_layout.visibility = if (model is CreateBookViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is CreateBookViewModel.UiModel.Content -> {
               categoryList = model.list
                initSpinner(categoryList)
            }

        }
    }

    private fun initSpinner( list : List<Category>){
        val categoryList = ArrayList<String>()
        categoryList.add("Select Category".toUpperCase())
        list.map {
            categoryList.add(it.name.toUpperCase())
        }
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        category_list_spinner.adapter = adapter

        category_list_spinner.setOnItemSelectedListener(this)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>, p1: View, position: Int, p3: Long) {
        if (!parent.getItemAtPosition(position).equals("Select Category".toUpperCase()))
            categorySelected = categoryList[position-1]
            print(categorySelected)
    }
}