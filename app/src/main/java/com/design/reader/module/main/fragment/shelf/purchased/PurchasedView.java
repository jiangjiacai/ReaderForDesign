package com.design.reader.module.main.fragment.shelf.purchased;


import com.design.reader.base.BaseView;
import com.design.reader.entity.BookInfo;

import java.util.List;

public interface PurchasedView extends BaseView {

    void showBookInfo(List<BookInfo> bookInfos);

    void showError(String msg);

}
