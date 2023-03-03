package com.server.sharemenu.common;

/**
 * An object implements an interface that an object serves to communicate with JasperReport
 */
public class MenuHeaderViewJasper implements MenuHeaderView{
    private String companyName;
    private String phone;
    private String email;

    public MenuHeaderViewJasper(MenuHeaderView menuHeaderView){
        this.companyName = menuHeaderView.getCompanyName();
        this.email = menuHeaderView.getEmail();
        this.phone = menuHeaderView.getPhone();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getCompanyName() {
        return this.companyName;
    }
}
