package mkworld29.mobile.com.cafemoa.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import mkworld29.mobile.com.cafemoa.adapter.MenuAdapter;
import mkworld29.mobile.com.cafemoa.R;
import mkworld29.mobile.com.cafemoa.entity.Cafe;

/**
 * Created by chmod777 on 2017. 6. 24..
 */

public class CafeFragment extends Fragment {

    Cafe cafe;

    public CafeFragment()
    {
        setCafe(null);
    }

    public void setCafe(Cafe cafe)
    {
        this.cafe = cafe;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_cafe, container, false);

        RecyclerView rv = (RecyclerView)layout.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(inflater.getContext(), 3);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLayoutManager);

        rv.setAdapter(new MenuAdapter(inflater.getContext(), cafe.getMenus()));

        return layout;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
