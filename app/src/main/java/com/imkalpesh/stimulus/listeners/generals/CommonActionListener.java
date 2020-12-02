package com.imkalpesh.stimulus.listeners.generals;

public interface CommonActionListener {

    void onViewClick();

    void onInfoClick(int itemPosition);

    void onEditClick(int itemPosition);

    void onDeleteClick(int itemPosition);

    void onChildClick(Object object);

    void onParentClick(Object object);

}
