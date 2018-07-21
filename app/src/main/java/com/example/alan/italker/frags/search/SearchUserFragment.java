package com.example.alan.italker.frags.search;

import com.alan.push.common.activities.BaseFragment;
import com.example.alan.italker.R;
import com.example.alan.italker.activities.SearchActivity;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public class SearchUserFragment extends BaseFragment
        implements SearchActivity.SearchFragment {
    @Override
    public Object getLayoutView() {
        return R.layout.fragment_search_user;
    }

    @Override
    public void search(String content) {

    }
}
