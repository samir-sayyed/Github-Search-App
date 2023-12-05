package com.sam.githubsearch.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.sam.githubsearch.R
import com.sam.githubsearch.databinding.FragmentProfileDetailsBinding
import com.sam.githubsearch.util.dismissLoadingDialog
import com.sam.githubsearch.util.showLoadingDialog


class ProfileDetailsFragment : Fragment() {

    companion object {
        private const val TAG = "ProfileDetailsFragment"
    }

    private val navArgs by navArgs<ProfileDetailsFragmentArgs>()
    private var viewBinding: FragmentProfileDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingDialog()
        initListeners()
        loadWebView()
    }

    private fun initListeners() {
        viewBinding?.backArrow?.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (viewBinding?.webview?.canGoBack() == true) {
                        viewBinding?.webview?.goBack()
                    } else {
                        remove()
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            })
    }

    private fun loadWebView() {
        viewBinding?.webview?.loadUrl(navArgs.repositoryUrl)
        viewBinding?.webview?.settings?.javaScriptEnabled = true
        viewBinding?.webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showLoadingDialog()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                dismissLoadingDialog()
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                dismissLoadingDialog()
                Log.e(TAG, "onReceivedError: $error")
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                super.onReceivedError(view, request, error)
            }
        }
    }
}