package com.nc.ocp.date_str_loc.locale.resourcebundles;

import com.nc.ocp.date_str_loc.locale.UsTaxCode;

import java.util.ListResourceBundle;

public class Tax_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {{"tax", new UsTaxCode(111)}};
    }
}
