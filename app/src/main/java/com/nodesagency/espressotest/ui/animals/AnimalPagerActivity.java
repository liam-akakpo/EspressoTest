package com.nodesagency.espressotest.ui.animals;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.nodesagency.espressotest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liamakakpo on 15/07/2016.
 */
public class AnimalPagerActivity extends AppCompatActivity implements AnimalCallback {

    @BindView(R.id.pager)
    ViewPager pager;

    private Vibrator vibrator = null;
    private AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        if (animalAdapter == null) {
            animalAdapter = new AnimalAdapter(getSupportFragmentManager());
        }
        pager.setAdapter(animalAdapter);
    }

    @Override
    public void onButtonClicked(Fragment fragment) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }

    @Override
    public Vibrator getVibrator() {
        if (vibrator == null) {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }
        return vibrator;
    }

    public class AnimalAdapter extends FragmentPagerAdapter {

        public AnimalAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Fragment getItem(int position) {
            int item = (position % 3);
            Log.e("ES", "position: " + position);
            switch (item) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}
