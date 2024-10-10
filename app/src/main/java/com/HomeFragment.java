package com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.andreabonatti92.chessboardrecyclerview.ChessboardAdapter;
import com.andreabonatti92.chessboardrecyclerview.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int matrixOrder = 8;
        ChessboardAdapter adapter = new ChessboardAdapter(matrixOrder);
        binding.chessboardRecyclerview.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), matrixOrder);
        binding.chessboardRecyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}