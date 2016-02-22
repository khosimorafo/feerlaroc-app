package co.moonmonkeylabs.quantum.adapters;

import android.util.SparseBooleanArray;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.quantum.model.Driver;

/**
 * Created by root on 2016/02/05.
 */
public class DriversAdapter extends FirebaseRecyclerAdapter<Driver, DriverHolder> {

    private SparseBooleanArray selectedItems = new SparseBooleanArray();;

    protected DriverHolder.SelectedItemListener mListener;

    public DriversAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Firebase ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    public DriversAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Firebase ref,
                          DriverHolder.SelectedItemListener listener) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(DriverHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        viewHolder.itemView.setSelected(selectedItems.get(position, false));
        viewHolder.setListener(mListener);

    }

    public void toggleSelection(int pos) {

        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }

        notifyChanged(pos);

    }

    void notifyChanged(int position) {
        notifyItemChanged(position);
    }

    public void removeSelectedItems(){
        for (Integer i : getSelectedItems()){
            toggleSelection(i);
        }
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

}
