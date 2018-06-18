package pl.politechnika.lodzka.budget_management.adapter;

/**
 * Created by Maciej on 2018-06-17.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import pl.politechnika.lodzka.budget_management.R;
import pl.politechnika.lodzka.budget_management.data.TransactionInfo;


/**
 * Created by Maciej on 2018-05-07.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<TransactionInfo> mTransactionInfos;

    public TransactionAdapter(List<TransactionInfo> transactionInfos){
        mTransactionInfos = transactionInfos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final static String TAG = TransactionAdapter.ViewHolder.class.getSimpleName();
        private final TextView valueTextView;
        private final TextView categoryTextView;
        private final TextView dateTextView;
        private final TextView descriptionTextView;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            valueTextView = v.findViewById(R.id.value);
            categoryTextView = v.findViewById(R.id.category);
            dateTextView = v.findViewById(R.id.date);
            descriptionTextView = v.findViewById(R.id.description);
        }

        public void setupWithTransactionInfo(TransactionInfo transactionInfo){
            valueTextView.setText(String.valueOf(transactionInfo.getValue()));
            categoryTextView.setText(transactionInfo.getCategory());
            dateTextView.setText(transactionInfo.getDate().toString());
            descriptionTextView.setText(transactionInfo.getDescription());
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionInfo TransactionInfo = mTransactionInfos.get(position);
        holder.setupWithTransactionInfo(TransactionInfo);
    }

    @Override
    public int getItemCount() {
        return mTransactionInfos.size();
    }


}
