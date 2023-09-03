package com.yns.wallet.widget.bottomsheet;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yns.wallet.util.MeasureUtil;

/**
 * Created by n on 2017/10/27.
 */

public class ListTextCenterAdapter extends RecyclerView.Adapter<ListTextCenterAdapter.Holder> {

    private Context context;
    private String[] data;
    private OnItemClickListener onItemClickListener;
    private BottomSheetDialog dialog;

    public ListTextCenterAdapter(Context context, String[] data,
                                 OnItemClickListener onItemClickListener, BottomSheetDialog dialog) {
        this.context = context;
        this.data = data;
        this.onItemClickListener = onItemClickListener;
        this.dialog = dialog;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(context);
        textView.setTextSize(14);
        textView.setHeight(MeasureUtil.dp(context, 42));
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new Holder(textView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        ((TextView) holder.itemView).setText(data[position]);
        holder.itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position);
            dialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
