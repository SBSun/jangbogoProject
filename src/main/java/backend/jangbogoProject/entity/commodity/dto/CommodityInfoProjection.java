package backend.jangbogoProject.entity.commodity.dto;

public interface CommodityInfoProjection {
    int getCommodity_Id();
    String getMarketName();
    int getCategory_Id();
    String getCategoryName();
    String getUnit();
    String getPrice();
    String getRemarks();
    String getP_Date();
}
