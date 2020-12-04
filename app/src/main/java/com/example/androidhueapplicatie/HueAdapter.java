package com.example.androidhueapplicatie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HueAdapter extends RecyclerView.Adapter<HueAdapter.ViewHolder> {

    private List<HueLight> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Switch switchButton;
        private TextView lampTitel;
        private TextView lampDescription;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            this.switchButton = view.findViewById(R.id.lampOnOffSwitch);
            this.lampTitel = view.findViewById(R.id.lampTitle);
            this.lampDescription = view.findViewById(R.id.lampDescription);
        }

//        public TextView getTextView() {
//            return textView;
//        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public HueAdapter(List<HueLight> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lamp_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.lampTitel.setText(localDataSet.get(position).getName());
        viewHolder.lampDescription.setText(localDataSet.get(position).getType());
        viewHolder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DataSingleton.getInstance().getManager().
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
//    public int getItemCount() {
//        return localDataSet.length;
//    }

}
