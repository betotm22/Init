package com.sye.base.bases;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sye.base.R;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements SimpleCallback {

    //region VARIABLES

    private FragmentManager fragmentManager;
    private final String CONST_PREFIX = "CONSTANT_";
    private String currentTag = "current";

    //endregion

    //region LIFE CYCLE

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    //endregion

    //region OUTPUT

    /**
     * Shows an error message in the monitor
     * @param message Message to show, it must be in strings file
     */
    public void le(String message) {
        Log.e("LOG_ERROR", message);
    }

    /**
     * Shows an error message in the monitor
     * @param message Message to show, it must be in strings file
     */
    public void li(String message) {
        Log.i("LOG_INFO", message);
    }

    //endregion

    //region FRAGMENT MANAGER

    /**
     * Method to add a fragment that is going to be use few times in the application lifetime.
     * To use this method, the layout resource must have a view group with id 'fragment_container';
     * by default, the method is gonna add on that view group.
     * @param fragment The fragment to add to the container.
     * @param containerId The id of the ViewGroup which will contain the fragment
     * @return It returns the tag assigned to the fragment so we can find it if necessary.
     */
    public String addFragment(Fragment fragment, int containerId) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(containerId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();

        return fragment.getTag();
    }

    public String addFragment(Fragment fragment, int containerId, SimpleCallback callback) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(containerId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();

        if (callback != null) ((BaseFragment) fragment).setCallback(callback);

        return fragment.getTag();
    }

    /**
     * Method to add a fragment that is going to be present in the whole application lifetime.
     * Its information is gonna be kept during the application lifetime. The fragment is won't
     * be removed or replaced, just will be hidden and shown.
     * To use this method, the layout resource must have a view group with id 'main_fragment_container';
     * by default, the method is gonna add on that view group.
     * @param fragment The fragment to add to the container.
     * @return It returns the tag assigned to the fragment so we can find it if necessary.
     */
    public String addConstantFragment(Fragment fragment) {

        String tag = CONST_PREFIX + fragment.getClass().getName();
        Fragment constant = fragmentManager.findFragmentByTag(tag);
        hideVisibleFragment(currentTag);

        if (constant != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .show(constant).commit();
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.main_fragment_container, fragment, tag)
                    .commit();
        }

        currentTag = fragment.getTag();
        return currentTag;
    }

    public String addConstantFragment(Fragment fragment, SimpleCallback callback) {

        String tag = CONST_PREFIX + fragment.getClass().getName();
        Fragment constant = fragmentManager.findFragmentByTag(tag);
        hideVisibleFragment(currentTag);

        if (constant != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .show(constant).commit();
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.main_fragment_container, fragment, tag)
                    .commit();
        }

        currentTag = fragment.getTag();
        if (callback != null) ((BaseFragment) fragment).setCallback(callback);
        return currentTag;
    }

    /**
     * Hides a constant fragment
     * @param tag Tag of the fragment that is going to be hidden
     * @return true if success
     */
    @SuppressWarnings("RestrictedApi")
    private boolean hideVisibleFragment(String tag) {

        if (fragmentManager.findFragmentByTag(tag) != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .hide(fragmentManager.findFragmentByTag(tag)).commit();
            return true;

        } else return false;
    }

    /**
     * This method show an already added fragment and will hide the one that is visible.
     * @param tag The tag obtained from {@link BaseActivity#addConstantFragment(Fragment)}
     */
    public String showConstant(String tag) {

        if (!fragmentManager.findFragmentByTag(tag).isVisible()) {
            hideVisibleFragment(currentTag);
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .show(fragmentManager.findFragmentByTag(tag))
                    .commit();
            currentTag = tag;
            return tag;
        }
        return currentTag;
    }

    /**
     * Removes an specific fragment using its tag
     * @param tag Tag of the fragment to be removed
     */
    public void remove(String tag) {

        if (tag.contains(CONST_PREFIX)) {
            onBackPressed();
        } else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .remove(fragmentManager.findFragmentByTag(tag))
                    .commit();
        }
    }


    /**
     * Removes the non-constant fragments in the stack
     */
    @SuppressLint("RestrictedApi")
    public void clearBackStack() {
        if (fragmentManager.getBackStackEntryCount() > 0) {

           List<Fragment> fragments = fragmentManager.getFragments();

            for (Fragment fragment : fragments) {
                if (!fragment.getTag().contains(CONST_PREFIX))
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .remove(fragmentManager.findFragmentByTag(fragment.getTag()))
                            .commit();
            }
        }
    }

    @Override
    public void sendBack(List<Object> list) {

    }

    @Override
    public void sendBack(Object... objects) {

    }

    //endregion
}