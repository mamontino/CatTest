package com.mamontov.cattest.screens.favorites

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
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
import com.mamontov.cattest.screens.createDialog
import com.mamontov.cattest.screens.openPermissionSettings
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
        private const val PERMISSION_CODE = 123
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

        presenter.getFavorites()
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
        adapter.clear()
        catList.visibility = View.VISIBLE
        emptyList.visibility = View.GONE
        adapter.addAll(cats)
    }

    override fun removeFavoriteItem(position: Int) {
        adapter.remove(position)
    }

    override fun showLoading() {
        catList.visibility = View.GONE
        emptyList.visibility = View.GONE
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
        createDialog(message) { _, _ ->  openPermissionSettings() }
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
