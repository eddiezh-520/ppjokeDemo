package com.demo.ppjokedemo.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.ppjokedemo.R;
import com.demo.ppjokedemo.model.BottomBar;
import com.demo.ppjokedemo.model.Destination;
import com.demo.ppjokedemo.util.AppConfig;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AppBottomBar extends BottomNavigationView {
    private static int[] sIcons = new int[]{R.drawable.icon_tab_home,R.drawable.icon_tab_sofa,R.drawable.icon_tab_publish,R.drawable.icon_tab_find,R.drawable.icon_tab_mine};
    public AppBottomBar(@NonNull Context context) {
        this(context,null);
    }

    public AppBottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("RestrictedApi")
    public AppBottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        BottomBar bottomBar = AppConfig.getBottomBar();
        List<BottomBar.Tab> tabs = bottomBar.tabs;

        //设置选中和未被选中的颜色
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{};

        int[] colors = new int[]{Color.parseColor(bottomBar.activeColor),Color.parseColor(bottomBar.inActiveColor)};
        ColorStateList colorStateList = new ColorStateList(states,colors);

        //给按钮设置对应状态和颜色
        setItemIconTintList(colorStateList);
        setItemTextColor(colorStateList);
        setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
        setSelectedItemId(bottomBar.selectTab);

        //将一个个的tab添加到bottomBar上
        for (int i = 0; i < tabs.size(); i++) {
            BottomBar.Tab tab = tabs.get(i);

            if (!tab.enable) {
                return;
            }

            int id = getId(tab.pageUrl);
            if (id < 0) {
                return;
            }
            MenuItem menuItem = getMenu().add(0, id, tab.index, tab.title);
            menuItem.setIcon(sIcons[tab.index]);
        }

        //设置按钮大小，另起一个forloop是因为getMenu().add每次add的时候都会remove item
        for (int i = 0; i < tabs.size(); i++) {
            BottomBar.Tab tab = tabs.get(i);
            int tabSize = dp2px(tab.size);

            BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView)getChildAt(0);
            BottomNavigationItemView itemView = (BottomNavigationItemView)bottomNavigationMenuView.getChildAt(tab.index);
            itemView.setIconSize(tabSize);

            if (TextUtils.isEmpty(tab.title)) {
                itemView.setIconTintList(ColorStateList.valueOf(Color.parseColor(tab.tintColor)));
                itemView.setShifting(false);
            }
        }
    }

    private int dp2px(int size) {
        float value = getContext().getResources().getDisplayMetrics().density * size + 0.5f;
        return (int)value;
    }

    private int getId(String pageUrl) {
        Destination destination = AppConfig.getDestConfig().get(pageUrl);
        if (destination == null) {
            return -1;
        }
        return destination.getId();
    }


}
