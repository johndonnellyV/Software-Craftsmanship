package Orange;

import java.lang.Object;
/**
 * Created by Jack Donnelly
 */
public enum ProductType {

    OPHONE ("oPhone"),
    OPAD ("oPad"),
    OPOD ("oPod"),
    OWATCH ("oWatch"),
    OTV ("oTv");


    protected String prodname;

    /**
     * ProductType constructor
     * @param prodname name of the ProductType
     */
    ProductType (String prodname){

        this.prodname = prodname;
    }

    public String getName(){

        return this.prodname;
    }

    /**
     * Checks if the Enum input is valid
     * @param prodname name of the ProductType
     * @return true if valid
     */
    public boolean isValidEnum(String prodname){
        ProductType[] producttypelist = ProductType.values();
        for (ProductType prodscan: producttypelist){
            if (prodscan.getName().equals(prodname)){
                return true;
            }
            return false;
        }
        return false;

    }

}
