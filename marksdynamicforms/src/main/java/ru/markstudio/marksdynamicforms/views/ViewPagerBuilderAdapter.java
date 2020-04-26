package ru.markstudio.marksdynamicforms.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import ru.markstudio.marksdynamicforms.R;

public class ViewPagerBuilderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private TypedArray images;
    private String [] headers;
    private String [] texts;


    public ViewPagerBuilderAdapter(Context context) {
        this.context = context;
        images = context.getResources().obtainTypedArray(R.array.builder_icons);
        headers = context.getResources().getStringArray(R.array.builder_titles);
        texts = context.getResources().getStringArray(R.array.builder_descriptions);
    }

    @Override
    public int getCount() {
        return images.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_pager2,null);

        ImageView imageView = view.findViewById(R.id.imageView_pager_ID);
        imageView.setImageResource(images.getResourceId(position, -1));

        TextView TextView_Page_headers = view.findViewById(R.id.textView_pager_header_ID);
        TextView_Page_headers.setText(headers[position]);

        TextView TextView_Page = view.findViewById(R.id.textView_pager_ID);
        TextView_Page.setText(texts[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
