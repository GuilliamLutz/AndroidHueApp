package com.example.androidhueapplicatie;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HueAdapter extends RecyclerView.Adapter<HueAdapter.ViewHolder> {

    private List<HueLight> localDataSet;

    public void updateData(List<HueLight> lights) {
        this.localDataSet = lights;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Switch switchButton;
        private TextView lampTitel;
        private TextView lampDescription;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

//            textView = (TextView) view.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
            this.switchButton = view.findViewById(R.id.lampOnOffSwitch);
            this.lampTitel = view.findViewById(R.id.lampTitle);
            this.lampDescription = view.findViewById(R.id.lampDescription);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            DataSingleton.getInstance().setPressedItem(clickedPosition);
            DataSingleton.getInstance().getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DetailLampActivity()).commit();
        }
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
                DataSingleton.getInstance().getManager().setOnState(b, position + 1 + "");
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
