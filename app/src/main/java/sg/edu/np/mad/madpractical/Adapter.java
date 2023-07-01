package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<User> users;
    Context context;
    AlertDialog.Builder builder;

    public Adapter (Context contextinput, ArrayList<User> userinput){
        users = userinput;
        context = contextinput;
    }

    @Override
    public int getItemViewType(int pos){
        User u = users.get(pos);
        if (u.name.endsWith("7")){
            return 0;
        }
        return 1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parentgrp, int viewlayout){
        if (viewlayout == 0){
            View item = LayoutInflater.from(parentgrp.getContext()).inflate(R.layout.recycler_user, parentgrp, false);
            return new ViewHolder(item);
        } else{
            View item2 = LayoutInflater.from(parentgrp.getContext()).inflate(R.layout.recycler, parentgrp, false);
            return new ViewHolder(item2);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos){
        User u2 = users.get(pos);
//        Log.i("ONBINDVIEWHOLDER", u2.name);

        holder.name.setText(u2.name);
//        Log.i("ONBINDVIEWHOLDER", u2.description);

        holder.desc.setText(u2.description);
        builder = new AlertDialog.Builder(context);
        holder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                builder.setTitle("Profile");
                builder.setMessage(u2.name);
                builder.setPositiveButton("VIEW",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent gvIntent = new Intent(context, MainActivity.class);
                                gvIntent.putExtra("key", u2.name);
                                Log.i("Adapter", u2.name);
                                context.startActivity(gvIntent);
                            }
                        });
                builder.setNegativeButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount(){ return users.size();}
}