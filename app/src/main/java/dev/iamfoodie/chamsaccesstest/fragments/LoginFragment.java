package dev.iamfoodie.chamsaccesstest.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.iamfoodie.chamsaccesstest.R;
import dev.iamfoodie.chamsaccesstest.activities.UsersActivity;
import dev.iamfoodie.chamsaccesstest.databinding.FragmentLoginBinding;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        !binding.emailEditText.getText().toString().isEmpty()
                        && !binding.passwordEditText.getText().toString().isEmpty()
                ) {
                    showProgressDialog();
                    login();
                } else {
                    Toast.makeText(getActivity(), "Empty fields!!", Toast.LENGTH_SHORT).show();
                }
//                showProgressDialog();
            }
        });

        binding.registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_loginFragment_to_registerFragment
                );
            }
        });
    }

    private void login() {
        loginViewModel.login(
                binding.emailEditText.getText().toString(),
                binding.passwordEditText.getText().toString(), getActivity()
        ).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    dismissDialog();
                    Intent intent = new Intent(getActivity(), UsersActivity.class);
                    intent.putExtra("currentUser", user);
                    startActivity(intent);
                }
            }
        });
    }

    private void showProgressDialog() {

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Logging you in...");
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }
}
