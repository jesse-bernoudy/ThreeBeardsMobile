package com.threebeardsmobile.taskapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;

public class ProjectDetailFragment extends Fragment {

    private OnProjectDetailFragmentListener callback;

    public Project getProject() {
        return project;
    }
    private Project project;

    public ProjectDetailFragment() {
    }

    public static ProjectDetailFragment newInstance(Project project) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setCurrentProject(project);
        return fragment;
    }

    // Container Activity must implement this interface
    public interface OnProjectDetailFragmentListener {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (OnProjectDetailFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnProjectItemFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_detail_view, container, false);

        return view;
    }

    private void setCurrentProject(Project project) {
        this.project = project;
    }
}
