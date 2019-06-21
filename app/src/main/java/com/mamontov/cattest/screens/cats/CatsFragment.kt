package com.mamontov.cattest.screens.cats

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.BaseFragment
import com.mamontov.cattest.screens.adapters.CatsAdapter
import com.mamontov.cattest.screens.adapters.EndlessScrollListener
import com.mamontov.cattest.screens.adapters.decorations.GridItemDecoration
import com.mamontov.cattest.screens.createDialog
import com.mamontov.cattest.screens.openPermissionSettings
import com.mamontov.cattest.screens.px
import com.mamontov.domain.entities.Cat
import com.mamontov.presentation.cats.CatsPresenter
import com.mamontov.presentation.cats.CatsView
import kotlinx.android.synthetic.main.fragment_cat.*
import javax.inject.Inject

class CatsFragment : BaseFragment(), CatsView {

    companion object {
        fun newInstance(): CatsFragment =
            CatsFragment()

        private const val ITEM_DIMEN = 16
        private const val SPAN_COUNT = 2
        private const val PERMISSION_CODE = 123
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: CatsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var scrollListener: EndlessScrollListener

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
        swipeContainer.setColorSchemeResources(R.color.colorPrimary)
        swipeContainer.setOnRefreshListener { presenter.refreshCats() }
        catList.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT, RecyclerView.VERTICAL, false)
        catList.addItemDecoration(
            GridItemDecoration(
                spacing = ITEM_DIMEN.px,
                spanCount = SPAN_COUNT
            )
        )
        catList.adapter = adapter

        scrollListener = EndlessScrollListener { presenter.loadMore() }
        catList.addOnScrollListener(scrollListener)
    }

    override fun updateFavoriteItem(position: Int, cat: Cat) {
        adapter.replace(position, cat)
    }

    override fun addCats(cats: List<Cat>) {
        emptyList.visibility = View.GONE
        catList.visibility = View.VISIBLE
        adapter.addAll(cats)
    }

    override fun clearCats() {
        adapter.clear()
    }

    override fun hideRefreshing() {
        swipeContainer.isRefreshing = false
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PERMISSION_CODE -> {
                    presenter.permissionGranted()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            presenter.permissionGranted()
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_LONG).show()
        }
        return
    }

    override fun checkPermission(cat: Cat) {
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            createDialog(R.string.storage_required) { _, _ ->  openPermissionSettings() }
        } else {
            requestStoragePermissions()
        }
    }

    private fun requestStoragePermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_CODE
        )
    }

    override fun showError(message: String) {
        createDialog(message) { _, _ ->  Unit }
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
