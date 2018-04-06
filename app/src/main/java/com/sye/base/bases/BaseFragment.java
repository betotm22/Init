package com.sye.base.bases;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sye.base.R;
import com.sye.base.util.Fonts;

import java.util.List;

/**
 * Created by qg-roberto on 5/05/17.
 */

public class BaseFragment extends Fragment implements SimpleCallback, BaseContractView {

    //region VARIABLES

    private FragmentManager fragmentManager;
    private final String CONST_PREFIX = "CONSTANT_";
    private SimpleCallback callback;

    private ProgressDialog progressDialog;

    //endregion

    //region LIFE CYCLE

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFonts(view);
    }

    /**
     * Changes the TypeFace of the fragment's view
     * @param view Fragment's view.
     */
    private void setFonts(View view) {
        Fonts.changeFonts(getActivity(), view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    //endregion

    //region UTIL

    public void setCallback(SimpleCallback callback){
        this.callback = callback;
    }

    public SimpleCallback getCallback() {
        return callback;
    }

    //endregion

    //region OUTPUT

    /**
     * Sets the title of the current activity
     * @param titleString Title to be set, must be in strings file
     * @return True if success
     */
    public boolean title(int titleString) {
        try {
            getActivity().setTitle(titleString);
            return true;
        } catch (NullPointerException exception) {
            return false;
        }
    }

    /**
     * Get the current title
     * @return The current fragment
     */
    public String title() {
        try {
            return getActivity().getTitle().toString();
        } catch (NullPointerException exception) {
            return getActivity().getResources().getString(R.string.app_name);
        }
    }

    /**
     * Shows a short Toast
     * @param message message to show in Toast, it must be in strings file
     */
    public void sT(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

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

    /**
     * Shows a red SnackBar
     * @param message Message in strings file to be displayed
     * @param shortSnack If true, shows a short SnackBar, else shows a long one
     */
    public void showError(int message, boolean shortSnack) {
        Snackbar snackbar = Snackbar.make(getView(), message,
                shortSnack ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.error));
        snackbar.show();
    }

    /**
     * Shows a green SnackBar using a String message.
     * @param message Message to be displayed.
     * @param shortSnack If true, shows a short SnackBar, else shows a long one.
     */
    public void showSuccess(int message, boolean shortSnack) {
        Snackbar snackbar = Snackbar.make(getView(), message,
                shortSnack ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.success));
        ((TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text))
                .setTextColor(getResources().getColor(R.color.text_primary_dark));
        snackbar.show();
    }

    /**
     * Shows a blue SnackBar using a String message.
     * @param message Message to be displayed.
     * @param shortSnack If true, shows a short SnackBar, else shows a long one.
     */
    public void showInfo(int message, boolean shortSnack) {
        Snackbar snackbar = Snackbar.make(getView(), message,
                shortSnack ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    //endregion

    //region FRAGMENT MANAGER

    /**
     * Method to add a fragment that is going to be use few times in the application lifetime.
     * To use this method, the layout resource must have a view group with id 'fragment_container';
     * by default, the method is gonna add on that view group.
     * @param fragment The fragment to add to the container.
     * @param viewGroupId The id of the ViewGroup which will contain the fragment
     * @return It returns the tag assigned to the fragment so we can find it if necessary.
     */
    public String addFragment(Fragment fragment, int viewGroupId) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(viewGroupId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();

        return fragment.getTag();
    }

    public String addFragment(Fragment fragment, int viewGroupId, SimpleCallback callback) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(viewGroupId, fragment, fragment.getClass().getName())
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
    public String addConstantFragment(Fragment fragment, SimpleCallback callback) {

        String tag = CONST_PREFIX + fragment.getClass().getName();
        Fragment constant = fragmentManager.findFragmentByTag(tag);
        hideVisibleFragment();

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

        if (callback != null) ((BaseFragment) fragment).setCallback(callback);

        return fragment.getTag();
    }

    /**
     * Hides a constant fragment
     * @return true if success
     */
    @SuppressWarnings("RestrictedApi")
    private boolean hideVisibleFragment() {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isVisible()) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .hide(fragment).commit();
                return true;
            }
        }
        return false;
    }

    /**
     * This method show an already added fragment and will hide the one that is visible.
     * @param tag The tag obtained from {@link BaseActivity#addConstantFragment(Fragment)}
     */
    public void showConstant(String tag) {

        if (!fragmentManager.findFragmentByTag(tag).isVisible()) {
            hideVisibleFragment();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .show(fragmentManager.findFragmentByTag(tag))
                    .commit();
        }
    }

    /**
     * Removes an specific fragment using its tag
     * @param tag Tag of the fragment to be removed
     */
    public void remove(String tag) {

        if (tag.contains(CONST_PREFIX)) {
            getActivity().onBackPressed();
        } else {
            if (fragmentManager.findFragmentByTag(tag) != null) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .remove(fragmentManager.findFragmentByTag(tag))
                        .commit();
            }
        }
    }


    /**
     * Removes the the fragment itself
     */
    public void removeSelf() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .remove(fragmentManager.findFragmentByTag(getTag()))
                .commit();
    }

    @Override
    public void sendBack(List<Object> list) {

    }

    @Override
    public void sendBack(Object... objects) {

    }

    @Override
    public void showSuccess(int message) {
        showSuccess(message, true);
    }

    @Override
    public void showError(int message) {
        showSuccess(message, true);

    }

    @Override
    public void progress(boolean show) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.dialog_message_loading));
        }
        if (show && !progressDialog.isShowing()) progressDialog.show();
        else if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    //endregion

}
