package sg.edu.np.mad.madpractical;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView name;
    TextView desc;

    public ViewHolder(View itemView){
        super(itemView);
        imageView = itemView.findViewById(R.id.imgView_r);
        name = itemView.findViewById(R.id.nameview_r);
        desc = itemView.findViewById(R.id.idview_r);
    }
    public ImageView getImageView(){
        return imageView;
    }
}
