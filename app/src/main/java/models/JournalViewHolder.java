package models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangcongtuan.diaryapp.R;

/**
 * Created by hoangcongtuan on 10/9/17.
 */

public class JournalViewHolder extends RecyclerView.ViewHolder {

    public ImageView journalIcon;
    public TextView tvTitle;
    public TextView tvDate;
    public ImageView btnDelete;

    public JournalViewHolder(View itemView) {
        super(itemView);
        journalIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        btnDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
    }
}
