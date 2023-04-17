package com.example.szakdolgozatfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CourseModel> courseModelArrayList;

    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<CourseModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        courseModelArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModel model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourseName());
        holder.courseDescTV.setText(model.getCourseDescription());
        holder.regulationTV.setText(model.getRegulationString());
        holder.licenseTV.setText(model.getLicenseString());
        holder.generalDataTV.setText(model.getGeneralData());
        holder.allowedInAgricultureTV.setText(model.getAllowedInAgriculture());
        holder.restrictionsTV.setText(model.getRestrictionString());
        holder.warningsTV.setText(model.getWarningString());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView courseNameTV;
        private final TextView courseDescTV;
        private final TextView regulationTV;
        private final TextView licenseTV;
        private final TextView generalDataTV;
        private final TextView allowedInAgricultureTV;
        private final TextView restrictionsTV;
        private final TextView warningsTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            regulationTV = itemView.findViewById(R.id.idTVRegulationString);
            licenseTV = itemView.findViewById(R.id.idTVLicenseString);
            generalDataTV = itemView.findViewById(R.id.idTVGeneralData);
            allowedInAgricultureTV = itemView.findViewById(R.id.idTVAllowedInAgriculture);
            restrictionsTV = itemView.findViewById(R.id.idTVRestrictions);
            warningsTV = itemView.findViewById(R.id.idTVWarnings);
        }
    }
}