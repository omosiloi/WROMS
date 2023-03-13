
package edu.scau.mis.view;

import edu.scau.mis.pos.domain.ProductDescription;
import edu.scau.mis.pos.mapper.ProductCatalog;
import edu.scau.mis.pos.service.ISaleService;
import edu.scau.mis.pos.vo.SaleItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 记账本
 * GRASP:控制器
 */
@Component
public class Register {
    @Autowired
    private ISaleService saleService;

    @Autowired
    private ProductCatalog productCatalog;

    /**
     * 开始一次新的销售
     */
    public void makeNewSale(){
        saleService.makeNewSale();

    }
    /**
     * 输入商品
     * @param itemSn
     * @param quantity
     */
    public SaleItemVo enterItem(String itemSn, int quantity){
        SaleItemVo saleItemVo = null;
        ProductDescription product = productCatalog.getProductByProductSn(itemSn);
        if(product != null) {
            saleService.makeLineItem(product, quantity);
            saleItemVo = new SaleItemVo(itemSn, product.getProductName(), product.getPrice(), quantity);
        }
        return saleItemVo;
    }
    /**
     * 结束销售
     */
    public BigDecimal endSale(){
        return saleService.endSale();
    }
    /**
     * 确认支付
     * @param cashTendered
     */
    public BigDecimal makePayment(BigDecimal cashTendered){
        return saleService.makePayment(cashTendered);
    }
}
