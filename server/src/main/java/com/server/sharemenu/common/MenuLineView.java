package com.server.sharemenu.common;

/**
 * The interface serves to artificially create a View object in which to retrieve data in a flat form
 */
public interface MenuLineView {
    String getItemCategoryName();
    String getItemName();
    String getItemDescription();
    Double getQuantity();
    String getItemUnit();
    Double getPrice();
}
