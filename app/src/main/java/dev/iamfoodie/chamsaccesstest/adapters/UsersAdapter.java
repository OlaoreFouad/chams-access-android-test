package dev.iamfoodie.chamsaccesstest.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.iamfoodie.chamsaccesstest.R;
import dev.iamfoodie.chamsaccesstest.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<User> users;
    private Context ctx;

    public UsersAdapter(List<User> users, Context ctx) {
        this.users = users;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View userView = inflater.inflate(R.layout.item_user, parent, false);

        return new UsersViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.user_email);
            name = itemView.findViewById(R.id.user_first_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ctx, "Adapter position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void bind(User user) {
            name.setText(user.getFirst_name() + " "  + user.getLast_name());
            email.setText(user.getEmail());
        }
    }

}
