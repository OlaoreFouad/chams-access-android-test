package dev.iamfoodie.chamsaccesstest.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.iamfoodie.chamsaccesstest.R;
import dev.iamfoodie.chamsaccesstest.activities.UsersActivity;
import dev.iamfoodie.chamsaccesstest.databinding.FragmentRegisterBinding;
import dev.iamfoodie.chamsaccesstest.models.User;
import dev.iamfoodie.chamsaccesstest.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (
                        !binding.firstnameEditText.getText().toString().isEmpty()
                        || !binding.emailEditText.getText().toString().isEmpty()
                        || !binding.lastnameEditText.getText().toString().isEmpty()
                        || !binding.passwordEditText.getText().toString().isEmpty()
                ) {
                    showProgressDialog();
                    register();
                } else {
                    Toast.makeText(getActivity(), "Empty fields!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_registerFragment_to_loginFragment
                );
            }
        });
    }

    private void register() {
        User user = new User(binding.firstnameEditText.getText().toString(),
                binding.lastnameEditText.getText().toString(), binding.emailEditText.getText().toString(),
                binding.passwordEditText.getText().toString(), "");

        registerViewModel.register(user, getActivity()).observe(this, new Observer<User>() {
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
        dialog.setMessage("Registering you...");
        dialog.setCancelable(true);
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }
}
