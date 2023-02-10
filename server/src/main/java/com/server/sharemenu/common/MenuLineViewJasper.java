package com.server.sharemenu.common;

//Обектър имплементира интерфейс, който обект служи за комуникация с JasperReport

public class MenuLineViewJasper implements MenuLineView {

    private String itemCategoryName;
    private String itemName;
    private String itemDescription;
    private Double quantity;
    private String itemUnit;
    private Double price;

    public MenuLineViewJasper(MenuLineView menuLineView) {
        this.itemCategoryName = "";
        this.itemName = menuLineView.getItemName();
        this.itemDescription = menuLineView.getItemDescription();
        this.quantity = menuLineView.getQuantity();
        this.itemUnit = menuLineView.getItemUnit();
        this.price = menuLineView.getPrice();
    }

    public MenuLineViewJasper(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    @Override
    public String getItemCategoryName() {
        return this.itemCategoryName;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String getItemDescription() {
        return this.itemDescription;
    }

    @Override
    public Double getQuantity() {
        return this.quantity;
    }

    @Override
    public String getItemUnit() {
        return this.itemUnit;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
