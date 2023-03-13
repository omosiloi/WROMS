package edu.scau.mis.pos.service.impl;

import edu.scau.mis.pos.domain.ProductDescription;
import edu.scau.mis.pos.mapper.ProductMapper;
import edu.scau.mis.pos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * 根据SN找产品
     * @param productSn
     * @return
     */
    @Override
    public ProductDescription getProductByProductSn(String productSn){
        return productMapper.getProductBySn(productSn);
    }

    /**
     * 列出所有产品
     * @return
     */
    @Override
    public List<ProductDescription> listAllProduct(){
        return productMapper.listAllProduct();
    }

    @Override
    public ProductDescription selectProductById(Long productId) {
        return productMapper.selectProductById(productId);
    }

    @Override
    public List<ProductDescription> selectProductList(ProductDescription productDescription) {
        return productMapper.selectProductList(productDescription);
    }

    @Override
    public int insertProduct(ProductDescription productDescription) {
        productDescription.setCreateTime(new Date());
        return productMapper.insertProduct(productDescription);
    }

    @Override
    public int updateProduct(ProductDescription productDescription) {
        productDescription.setUpdateTime(new Date());
        return productMapper.updateProduct(productDescription);
    }

    @Override
    public int deleteProductById(Long productId) {
        return productMapper.deleteProductById(productId);
    }

}
