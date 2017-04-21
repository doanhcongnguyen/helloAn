package com.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alpha.com.newhelllo.R;

/**
 * Created by alpha on 4/21/2017.
 */

public class ContactAdapter extends ArrayAdapter {

    List<Contact> list = new ArrayList<>();

    public ContactAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Contact contact) {
        super.add(contact);
        list.add(contact);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Contact getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ContactHolder contactHolder;
        if (row != null) {
            contactHolder = (ContactHolder) row.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            contactHolder = new ContactHolder();
            contactHolder.tvName = (TextView) row.findViewById(R.id.tvName);
            contactHolder.tvEmail = (TextView) row.findViewById(R.id.tvEmail);
            contactHolder.tvContact = (TextView) row.findViewById(R.id.tvContact);
            contactHolder.tvPassword = (TextView) row.findViewById(R.id.tvPassword);
            row.setTag(contactHolder);
        }
        Contact contact = this.getItem(position);
        contactHolder.tvName.setText(contact.getName());
        contactHolder.tvEmail.setText(contact.getEmail());
        contactHolder.tvContact.setText(contact.getContact());
        contactHolder.tvPassword.setText(contact.getPassword());
        return row;
    }

    static class ContactHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvContact;
        TextView tvPassword;
    }
}
