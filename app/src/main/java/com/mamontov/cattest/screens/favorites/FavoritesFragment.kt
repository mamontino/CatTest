package com.mamontov.cattest.screens.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.BaseFragment
import com.mamontov.cattest.screens.adapters.CatsAdapter
import com.mamontov.cattest.screens.adapters.decorations.LinearItemDecoration
import com.mamontov.cattest.screens.px
import com.mamontov.domain.entities.Cat
import com.mamontov.presentation.favorites.FavoritesPresenter
import com.mamontov.presentation.favorites.FavoritesView
import kotlinx.android.synthetic.main.fragment_cat.*
import javax.inject.Inject

class FavoritesFragment : BaseFragment(), FavoritesView {

    companion object {
        fun newInstance(): FavoritesFragment =
            FavoritesFragment()

        private const val ITEM_DIMEN = 16
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter: CatsAdapter =
        CatsAdapter(
            { position, cat -> presenter.onFavoritesClicked(position, cat) },
            { presenter.onImageClicked(it) }
        )

    override val contentLayout: Int = R.layout.fragment_cat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        swipeContainer.isRefreshing = false
        swipeContainer.isEnabled = false
        catList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        catList.addItemDecoration(LinearItemDecoration(spacing = ITEM_DIMEN.px))
        catList.adapter = adapter
    }

    override fun addCats(cats: List<Cat>) {
        catList.visibility = View.VISIBLE
        emptyList.visibility = View.GONE
        adapter.addAll(cats)
    }

    override fun removeFavoriteItem(cat: Cat) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showEmptyCats() {
        catList.visibility = View.GONE
        progress.visibility = View.GONE
        emptyList.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun checkPermission(cat: Cat) {
        Toast.makeText(requireContext(), cat.url, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun updateFavoriteItem(position: Int, cat: Cat) {
        adapter.replace(position, cat)
    }
}
