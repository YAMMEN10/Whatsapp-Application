package com.example.whatsapp.ui.Activitys.MainActivity.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp.ui.Fragments.ChatFragment.ChatsFragment;
import com.example.whatsapp.ui.Fragments.ContactsFragment.ContactsFragment;
import com.example.whatsapp.ui.Fragments.GroupsFragment.GroupsFragment;

public class $_TabsAccessAdapter extends FragmentPagerAdapter {
    private Fragment main_page_fragment[];

    public $_TabsAccessAdapter(FragmentManager fm, int count_main_page_adapter) {
        super(fm);
        this.main_page_fragment = new Fragment[count_main_page_adapter];
    }


    @Override
    public int getCount() {
        return this.main_page_fragment.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                this.main_page_fragment[position] = new ChatsFragment();
                return this.main_page_fragment[position];

            case 1:
                this.main_page_fragment[position] = new GroupsFragment();
                return this.main_page_fragment[position];

            case 2:
                this.main_page_fragment[position] = new ContactsFragment();
                return this.main_page_fragment[position];
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chats";

            case 1:
                return "Groups";

            case 2:
                return "Contacts";
            default:
                return null;
        }
    }
}
