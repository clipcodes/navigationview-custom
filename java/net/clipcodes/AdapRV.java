package net.clipcodes;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapRV extends RecyclerView.Adapter<AdapRV.ViewHolder>{

    private ItemClickListener onItemClickListener;
    Activity activity;
    String[] title;
    int[] icon;
    int[] color;

    public AdapRV(Activity activity, int[] icon, String[] title, int[] color){
        this.activity = activity;
        this.icon = icon;
        this.title = title;
        this.color = color;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.modelitem_menu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.menuname.setText(title[position]);
        holder.menuicon.setImageResource(icon[position]);
        holder.menuicon.setColorFilter(activity.getResources().getColor(color[position]));

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        FrameLayout containeritem;
        TextView menuname;
        ImageView menuicon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            menuname = itemView.findViewById(R.id.menuname);
            menuicon = itemView.findViewById(R.id.menuicon);
            containeritem = itemView.findViewById(R.id.containeritem);
        }
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}