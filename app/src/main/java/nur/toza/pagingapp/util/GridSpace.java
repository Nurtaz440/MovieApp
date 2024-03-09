package nur.toza.pagingapp.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpace extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpace(int space, int spacing, boolean includeEdge) {
        this.spanCount = space;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); //item postion
        int colum = position % spanCount;  // item column

        if (includeEdge){
            outRect.left = spacing - colum*spacing/spanCount; // spacing-column * ((1f / spanCount) * spacing)
            outRect.right = (colum + 1) * spacing / spanCount; // (column+1) * ((1f / spanCount) * spacing)

            if (position < spanCount){ //topEdge
                outRect.top = spacing;

            }
            outRect.bottom = spacing; //item bottom
        }else {
            outRect.left = colum * spacing / spanCount; //column *((1f / spanCount) * spacing)
            outRect.right = spacing - (colum+1) * spacing / spanCount; // spacing - (column + 1) * ((1f /spanCount)*spacing)
            if (position>=spanCount){
                outRect.top = spacing; //item top
            }
        }
    }
}
