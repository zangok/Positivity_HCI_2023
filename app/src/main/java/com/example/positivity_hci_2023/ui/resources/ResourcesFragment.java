package com.example.positivity_hci_2023.ui.resources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.positivity_hci_2023.R;
import com.example.positivity_hci_2023.databinding.FragmentResourcesBinding;

public class ResourcesFragment extends Fragment {

    private FragmentResourcesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResourcesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the WebView
        WebView webView = binding.webviewResources;
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if needed
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Show the WebView when the page is finished loading
                webView.setVisibility(View.VISIBLE);
            }
        });

        // Buttons to load the local HTML files
        Button buttonWikipedia = root.findViewById(R.id.buttonWikipedia);
        buttonWikipedia.setOnClickListener(v -> {
            webView.loadUrl("file:///android_asset/problematic_smartphone_use_wikipedia.html");
            toggleWebViewVisibility(true);
        });

        Button buttonSamhsa = root.findViewById(R.id.buttonSamhsa);
        buttonSamhsa.setOnClickListener(v -> {
            webView.loadUrl("file:///android_asset/samhsa_national_helpline.html");
            toggleWebViewVisibility(true);
        });

        return root;
    }

    private void toggleWebViewVisibility(boolean show) {
        // If true, show the WebView
        binding.webviewResources.setVisibility(show ? View.VISIBLE : View.GONE);
        // If false, show the buttons
        binding.buttonWikipedia.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.buttonSamhsa.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public boolean canGoBack() {
        if (binding.webviewResources.getVisibility() == View.VISIBLE && binding.webviewResources.canGoBack()) {
            binding.webviewResources.goBack();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding.webviewResources != null) {
            binding.webviewResources.destroy();
        }
        binding = null;
    }
}
