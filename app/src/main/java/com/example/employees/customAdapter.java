package com.example.employees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> implements Filterable
{
    private Context context;
    private ArrayList employee_id,employee_name,employee_designation;
    ArrayList<String> employeeName;



    public customAdapter(Context context, ArrayList employee_id, ArrayList employee_name, ArrayList employee_designation)
    {
        this.context = context;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employeeName = new ArrayList<>(employee_name);
        this.employee_designation = employee_designation;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        holder.employee_name_txt.setText(String.valueOf(employee_name.get(position)));
        holder.employee_designation_txt.setText(String.valueOf(employee_designation.get(position)));

    }

    @Override
    public int getItemCount() {
        return employee_id.size();
    }

    @Override
    public Filter getFilter()
    {
        return filter;
    }
    Filter filter = new Filter()
    {


        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {

            ArrayList<String> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty())
            {
                filteredList.addAll(employeeName);
            }
            else {
                for (String name : employeeName)
                {
                    if (name.toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredList.add(name);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
                employee_name.clear();
                employee_name.addAll((Collection) filterResults.values);
                notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView employee_name_txt,employee_designation_txt;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            employee_name_txt = itemView.findViewById(R.id.employee_name_text);
            employee_designation_txt = itemView.findViewById(R.id.employee_designation_txt);

        }
    }
}
