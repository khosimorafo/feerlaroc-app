package com.feerlaroc.invoices.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by root on 2016/02/23.
 */
public abstract class ZohoRecyclerViewAdapter <VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected int mModelLayout;
    Class<VH> mViewHolderClass;
    protected ZohoEntityArray mEntities;

    /**
     * @param modelLayout This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                    instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param //ref        The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                   combination of <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>
     */
    public ZohoRecyclerViewAdapter(String key, int modelLayout, Class<VH> viewHolderClass) {

        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;

        mEntities = new ZohoEntityArray(key);

        mEntities.setOnChangedListener(new ZohoEntityArray.OnDataChangedListener() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    /**
     * @param modelLayout This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                    instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param //ref        The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                   combination of <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>
     */
    public ZohoRecyclerViewAdapter(String key, String id, int modelLayout, Class<VH> viewHolderClass) {

        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;

        mEntities = new ZohoEntityArray(key, id);

        mEntities.setOnChangedListener(new ZohoEntityArray.OnDataChangedListener() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);

        try {

            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        Map<String, Object> model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }

    /**
     * Each time the data at the given Firebase location changes, this method will be called for each item that needs
     * to be displayed. The first two arguments correspond to the mLayout and mModelClass given to the constructor of
     * this class. The third argument is the item's position in the list.
     * <p>
     * Your implementation should populate the view using the data contained in the model.
     *
     * @param viewHolder The view to populate
     * @param model      The object containing the data used to populate the view
     * @param position  The position in the list of the view being populated
     */
    abstract protected void populateViewHolder(VH viewHolder, Map<String,Object> model, int position);

    public Map<String, Object> getItem(int position) {
        return mEntities.getItem(position);
    }


    @Override
    public int getItemCount() {
        return mEntities.getArraySize();
    }

}
