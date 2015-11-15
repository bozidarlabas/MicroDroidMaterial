package com.bozidar.microdroid.transition;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for creating content transitions.
 */
public class TransitionHelper {

    /**
     * Method which is called when user wants to make transition from one activity to another
     */
    public void transitionToActivity(Class target, Activity fromActivity, View transitionView, int transitionName) {
        Log.d("Item", "Item2");
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(fromActivity, false,
                new Pair<>(transitionView, fromActivity.getString(transitionName)));
        startActivity(fromActivity, target, pairs, null);
    }

    /**
     * Method which is called when user wants to make transition from one activity to another
     */
    public void transitionToActivity(Class target, Activity fromActivity, View transitionView, String transitionName, String... intentData) {
        Log.d("Item", "Item2");
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(fromActivity, false,
                new Pair<>(transitionView, transitionName));

        ArrayList<String> intentAllData = new ArrayList<>();
        if(intentData != null && intentData.length > 0){
            intentAllData.addAll(Arrays.asList(intentData));
        }

        startActivity(fromActivity, target, pairs, intentAllData);
    }

    /**
     * Method which start transition animation and start new activity
     */
    private void startActivity(Activity fromActivity, Class target, Pair<View, String>[] pairs, ArrayList<String> intentAllData) {
        Intent intent = new Intent(fromActivity, target);
        if(intentAllData != null){
            for(int i = 0; i < intentAllData.size(); i++ ){
                intent.putExtra(i + "", intentAllData.get(i));
            }
        }
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(fromActivity, pairs);
        fromActivity.startActivity(intent, transitionActivityOptions.toBundle());
    }

    /**
     * Create the transition participants required during a activity transition while
     * avoiding glitches with the system UI.
     */

    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar, @Nullable Pair... otherParticipants) {
        // Avoid system UI glitches as described here:
        // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        // Create pair of transition participants.
        List<Pair> participants = new ArrayList<>(3);
        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);
        // only add transition participants if there's at least one none-null element
        if (otherParticipants != null) {
            Log.d("unutraaa sam", "etst");
            participants.addAll(Arrays.asList(otherParticipants));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

    /**
     * Check if participant view in activity transition is not null
     */
    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new Pair<>(view, view.getTransitionName()));
    }
}
