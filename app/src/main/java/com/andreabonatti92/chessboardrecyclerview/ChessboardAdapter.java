package com.andreabonatti92.chessboardrecyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreabonatti92.chessboardrecyclerview.databinding.ChessboardSquare4x4Binding;
import com.andreabonatti92.chessboardrecyclerview.databinding.ChessboardSquare8x8Binding;

import java.util.HashMap;

public class ChessboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int matrixOrder;
    private HashMap<Integer, Boolean> selectionMatrix = new HashMap<>();
    private int evenSelectedColor, evenNotSelectedColor, oddSelectedColor, oddNotSelectedColor;

    public ChessboardAdapter(int matrixOrder) {
        this.matrixOrder = matrixOrder;
        for (int i = 0; i < matrixOrder * matrixOrder; i++) {
            selectionMatrix.put(i, false);
        }
        evenSelectedColor = Color.RED;
        evenNotSelectedColor = Color.YELLOW;
        oddSelectedColor = Color.BLUE;
        oddNotSelectedColor = Color.GREEN;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (matrixOrder == 4) {
            return new ChessboardSquare4x4ViewHolder(
                    ChessboardSquare4x4Binding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        } else {
            return new ChessboardSquare8x8ViewHolder(
                    ChessboardSquare8x8Binding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ChessboardSquare4x4ViewHolder) {
            drawSquare(((ChessboardSquare4x4ViewHolder) holder).binding.getRoot(), position);
            handleClick(((ChessboardSquare4x4ViewHolder) holder).binding.getRoot(), position);
        } else {
            drawSquare(((ChessboardSquare8x8ViewHolder) holder).binding.getRoot(), position);
            handleClick(((ChessboardSquare8x8ViewHolder) holder).binding.getRoot(), position);
        }
    }

    @Override
    public int getItemCount() {
        return matrixOrder * matrixOrder;
    }

    public static class ChessboardSquare4x4ViewHolder extends RecyclerView.ViewHolder {
        private ChessboardSquare4x4Binding binding;

        public ChessboardSquare4x4ViewHolder(ChessboardSquare4x4Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ChessboardSquare8x8ViewHolder extends RecyclerView.ViewHolder {
        private ChessboardSquare8x8Binding binding;

        public ChessboardSquare8x8ViewHolder(ChessboardSquare8x8Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private boolean isEven(int number) {
        return ((number % 2) == 0);
    }

    private void drawSquare(View view, int position) {
        boolean positionInOddRow = (position % (2 * matrixOrder)) >= 0 && (position % (2 * matrixOrder)) <= (matrixOrder - 1);
        boolean positionIsSelected = Boolean.TRUE.equals(selectionMatrix.get(position));
        if (positionInOddRow) {
            if (isEven(position)) {
                if (positionIsSelected) {
                    view.setBackgroundColor(evenSelectedColor);
                } else {
                    view.setBackgroundColor(evenNotSelectedColor);
                }
            } else {
                if (positionIsSelected) {
                    view.setBackgroundColor(oddSelectedColor);
                } else {
                    view.setBackgroundColor(oddNotSelectedColor);
                }
            }
        } else {
            if (isEven(position)) {
                if (positionIsSelected) {
                    view.setBackgroundColor(oddSelectedColor);
                } else {
                    view.setBackgroundColor(oddNotSelectedColor);
                }
            } else {
                if (positionIsSelected) {
                    view.setBackgroundColor(evenSelectedColor);
                } else {
                    view.setBackgroundColor(evenNotSelectedColor);
                }
            }
        }
    }

    private void handleClick(View view, int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean selectedValue = Boolean.TRUE.equals(selectionMatrix.get(position));
                boolean newValue = !selectedValue;
                selectionMatrix.put(position, newValue);
                notifyItemChanged(position);
            }
        });
    }
}
