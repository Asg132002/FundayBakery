package com.example.medicinesupply;

import android.widget.Button;

public class MenuItemsModel
{
    String cakeNameMd, cakeDetailsMd;
    int cakeImgMd;

    public MenuItemsModel(String cakeNameMd, String cakeDetailsMd, int cakeImgMd) {
        this.cakeNameMd = cakeNameMd;
        this.cakeDetailsMd = cakeDetailsMd;
        this.cakeImgMd = cakeImgMd;
    }

    public String getCakeName() {
        return cakeNameMd;
    }

    public String getCakeDetails()
    {
        return cakeDetailsMd;
    }

    public int getCakeImg()
    {
        return cakeImgMd;
    }
    
}
