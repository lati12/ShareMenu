package com.server.sharemenu.common;

/**
 * The interface serves to artificially create a View object in which to retrieve data in a flat form
 */

public interface MenuHeaderView {
    String getName();
    String getAddress();
    String getPhone();
    String getEmail();
    String getCity();
    String getCompanyName();
}
