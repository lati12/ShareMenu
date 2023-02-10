package com.server.sharemenu.common;

//Интерфейсът служи за изкуствено създаване на View oбект, в който да се извлекат данни в плосък вид

public interface MenuLineView {
    String getItemCategoryName();
    String getItemName();
    String getItemDescription();
    Double getQuantity();
    String getItemUnit();
    Double getPrice();
}
