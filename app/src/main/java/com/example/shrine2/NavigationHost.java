package com.example.shrine2;

import androidx.fragment.app.Fragment;

public interface NavigationHost {

    void navigateTo(Fragment fragment, boolean addToStackTrace);
}
