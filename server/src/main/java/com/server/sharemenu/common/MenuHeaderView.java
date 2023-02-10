package com.server.sharemenu.common;

//Интерфейсът служи за изкуствено създаване на View oбект, в който да се извлекат данни в плосък вид

public interface MenuHeaderView {
    String getName();
    String getAddress();
    String getPhone();
    String getEmail();
    String getCity();
    String getCompanyName();
}
